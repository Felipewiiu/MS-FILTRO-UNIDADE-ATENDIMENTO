package br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.controller;

import br.com.example.upafacil.ms_filtro_unidade_atendimento.application.usecases.*;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.entities.Upa;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto.UpaDto;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.dto.UpaLocationDto;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.mapper.UpaDtoMapper;
import br.com.example.upafacil.ms_filtro_unidade_atendimento.presentation.mapper.UpaLocationMapper;
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


    @GetMapping(value = "/real-time-queue", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UpaDto> streamCapacityUpdates() {
        return eventoSink.asFlux();
    }


    @PostMapping("/create")
    public Mono<ResponseEntity<UpaDto>> createUpa(@RequestBody UpaDto upaDto) {
        Mono<Upa> upa = createUpaUseCase.createUpa(upaDtoMapper.toDomain(upaDto));

        return upa.map(upas -> ResponseEntity.ok(upaDtoMapper.toDto(upas)));

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UpaDto>> findUpaByUpaId(@PathVariable("id") Long upaId) {
        Mono<Upa> upa = findUpaByIdUseCase.findUpaById(upaId);
        return upa.map(upas -> ResponseEntity.status(HttpStatus.OK).body(upaDtoMapper.toDto(upas)));
    }

    @GetMapping("/all")
    public Mono<ResponseEntity<Flux<UpaDto>>> findAllUpa() {
        Flux<UpaDto> upaDtoFlux = findAllUpasUseCase.findAllUpa()
                .map(upaDtoMapper::toDto);

        return Mono.just(ResponseEntity.accepted().body(upaDtoFlux));
    }


    @GetMapping("/near-upa")
    public Mono<UpaLocationDto> getNearestUpa(@RequestParam Double latitude, @RequestParam Double longitude) {
        Mono<Upa> upaDomain = findNearestUpaUseCase.findNearestUpa(latitude, longitude);

        return upaDomain.map(upaLocationMapper::toDto);
    }

    @GetMapping("/lower-queue/state/{state}")
    public Mono<ResponseEntity<UpaDto>> findUpaWithLowerCapacity(@PathVariable("state") Integer state) {
        Mono<Upa> upa = findUpaWithLowerCapacityUseCase.findUpaWithLowerCapacity(state);

        return upa.map(upas -> ResponseEntity.ok(upaDtoMapper.toDto(upas)));
    }

    @GetMapping("/register-service/{upaId}")
    public Mono<UpaDto> registerServiceUpa(@PathVariable("upaId") Long upaId) {
        Mono<Upa> upa = reduceServiceCapacityUpaUseCase.reduceServiceCapacityUpa(upaId);
        return upa.map(upaDtoMapper::toDto).doOnSuccess(eventoSink::tryEmitNext);

    }

    @GetMapping("/frees-queue/{upaId}")
    public Mono<UpaDto> freesUpaCapacity(@PathVariable("upaId") Long upaId) {
        return freesUpaCapacityUpaUseCase.freesCapacity(upaId)
                .map(upaDtoMapper::toDto).doOnSuccess(eventoSink::tryEmitNext);
    }


    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteUpa(@PathVariable("id") Long upaId) {
        return deletUpaUseCase.deleteUpaById(upaId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @PatchMapping("/update/{id}")
    public Mono<ResponseEntity<UpaDto>> updateUpa(
            @PathVariable("id") Long upaId,
            @Valid @RequestBody UpaDto upaDto) {

        return updateUpaUseCase.updateUpa(upaId, upaDtoMapper.toDomain(upaDto))
                .map(updatedUpa -> ResponseEntity.ok(upaDtoMapper.toDto(updatedUpa)));

    }

}
