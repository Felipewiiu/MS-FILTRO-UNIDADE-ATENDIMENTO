package br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.controller;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.usecases.*;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto.UpaDto;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto.UpaLocationDto;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.mapper.UpaDtoMapper;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.mapper.UpaLocationMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upa")
public class UpaController {

    private final CreateUpaUseCase createUpaUseCase;
    private final UpaDtoMapper upaDtoMapper;
    private final FindUpaByIdUseCase findUpaByIdUseCase;
    private final FindAllUpasUseCase findAllUpasUseCase;
    private final DeletUpaUseCase deletUpaUseCase;
    private final UpdateUpaUseCase updateUpaUseCase;
    private final FindNearestUpaUseCase findNearestUpaUseCase;
    private final UpaLocationMapper upaLocationMapper;
    private final FindUpaWithLowerCapacity findUpaWithLowerCapacityUseCase;
    private final ReduceServiceCapacityUpa reduceServiceCapacityUpaUseCase;
    private final FreesUpaCapacityUpa freesUpaCapacityUpaUseCase;
    private final Sinks.Many<UpaDto> eventoSink = Sinks.many().multicast().onBackpressureBuffer();


    @Operation(summary = "Acompanha a fila de atendimento em tempo real")
    @GetMapping(value = "/real-time-queue", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UpaDto> streamCapacityUpdates() {
        return eventoSink.asFlux();
    }


    @Operation(summary = "Cadastra uma nova UPA")
    @PostMapping("/create")
    public Mono<ResponseEntity<UpaDto>> createUpa(@RequestBody UpaDto upaDto) {
        return createUpaUseCase.createUpa(upaDtoMapper.toDomain(upaDto))
                .map(upas -> new ResponseEntity<>(upaDtoMapper.toDto(upas), HttpStatus.CREATED));

    }

    @Operation(summary = "Busca uma UPA pelo ID")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<UpaDto>> findUpaByUpaId(@PathVariable("id") Long upaId) {
        Mono<Upa> upa = findUpaByIdUseCase.findUpaById(upaId);
        return upa.map(upas -> ResponseEntity.status(HttpStatus.OK).body(upaDtoMapper.toDto(upas)));
    }

    @Operation(summary = "Retorna todas as UPAs disponíveis")
    @GetMapping("/all")
    public Mono<ResponseEntity<Flux<UpaDto>>> findAllUpa() {
        Flux<UpaDto> upaDtoFlux = findAllUpasUseCase.findAllUpa()
                .map(upaDtoMapper::toDto);

        return Mono.just(ResponseEntity.accepted().body(upaDtoFlux));
    }


    @Operation(summary = "Retorna a UPAs mais próxima do paciente segundo sua localização")
    @GetMapping("/near-upa")
    public Flux<UpaLocationDto> getNearestUpa(@RequestParam Double latitude, @RequestParam Double longitude) {
        Flux<Upa> upaDomain = findNearestUpaUseCase.findNearestUpa(latitude, longitude);

        return upaDomain.map(upaLocationMapper::toDto);
    }

    @Operation(summary = "Retorna a UPA com menor fila de atendimento")
    @GetMapping("/lower-queue/state/{state}")
    public Mono<ResponseEntity<UpaDto>> findUpaWithLowerQueue(@PathVariable("state") Integer state) {
        Mono<Upa> upa = findUpaWithLowerCapacityUseCase.findUpaWithLowerCapacity(state);

        return upa.map(upas -> ResponseEntity.ok(upaDtoMapper.toDto(upas)));
    }

    @Operation(summary = "Adiciona paciente na fila de atendimento")
    @GetMapping("/register-service/{upaId}")
    public Mono<UpaDto> registerServiceUpa(@PathVariable("upaId") Long upaId) {
        Mono<Upa> upa = reduceServiceCapacityUpaUseCase.reduceServiceCapacityUpa(upaId);
        return upa.map(upaDtoMapper::toDto).doOnSuccess(eventoSink::tryEmitNext);

    }

    @Operation(summary = "Retira paciente da fila de atendimento")
    @GetMapping("/frees-queue/{upaId}")
    public Mono<UpaDto> freesUpaCapacity(@PathVariable("upaId") Long upaId) {
        return freesUpaCapacityUpaUseCase.freesCapacity(upaId)
                .map(upaDtoMapper::toDto).doOnSuccess(eventoSink::tryEmitNext);
    }


    @Operation(summary = "Remove uma UPA do sistema")
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteUpa(@PathVariable("id") Long upaId) {
        return deletUpaUseCase.deleteUpaById(upaId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @Operation(summary = "Atualiza informações da UPA")
    @PatchMapping("/update/{id}")
    public Mono<ResponseEntity<UpaDto>> updateUpa(
            @PathVariable("id") Long upaId,
            @Valid @RequestBody UpaDto upaDto) {

        return updateUpaUseCase.updateUpa(upaId, upaDtoMapper.toDomain(upaDto))
                .map(updatedUpa -> ResponseEntity.ok(upaDtoMapper.toDto(updatedUpa)));

    }

}
