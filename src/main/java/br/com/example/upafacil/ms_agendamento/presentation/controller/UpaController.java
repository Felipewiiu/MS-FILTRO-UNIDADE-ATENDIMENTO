package br.com.example.upafacil.ms_agendamento.presentation.controller;

import br.com.example.upafacil.ms_agendamento.application.usecases.*;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.presentation.dto.UpaDto;
import br.com.example.upafacil.ms_agendamento.presentation.dto.UpaLocationDto;
import br.com.example.upafacil.ms_agendamento.presentation.mapper.UpaDtoMapper;
import br.com.example.upafacil.ms_agendamento.presentation.mapper.UpaLocationMapper;
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
    private final Sinks.Many<UpaDto> eventoSink = Sinks.many().multicast().onBackpressureBuffer();

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
        Mono<Upa> upaDomain =  findNearestUpaUseCase.findNearestUpa(latitude, longitude);

        return upaDomain.map(upaLocationMapper::toDto);
    }

    @GetMapping("/lower-capacity/state/{state}")
    public Mono<ResponseEntity<UpaDto>> findUpaWithLowerCapacity(@PathVariable("state") Integer state) {
        Mono<Upa> upa = findUpaWithLowerCapacityUseCase.findUpaWithLowerCapacity(state);

        return upa.map(upas -> ResponseEntity.ok(upaDtoMapper.toDto(upas)));
    }

    @GetMapping("/register-service/{upaId}")
    public Mono<UpaDto> registerServiceUpa(@PathVariable("upaId") Long upaId) {
        Mono<Upa> upa = reduceServiceCapacityUpaUseCase.reduceServiceCapacityUpa(upaId);
        return upa.map(upaDtoMapper::toDto).doOnSuccess(dto -> {
                    System.out.println("Capacidade atualizada: " + dto.capacityUsed());
                    eventoSink.tryEmitNext(dto);
                })
                .doOnError(error -> System.err.println("Erro: " + error.getMessage()));
    }

    @GetMapping(value = "/capacity-updates", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UpaDto> streamCapacityUpdates() {
        return eventoSink.asFlux()
                .doOnSubscribe(sub -> System.out.println("Cliente conectado ao /capacity-updates"))
                .doOnNext(dto -> System.out.println("Enviando evento SSE: " + dto.capacityUsed()))
                .doOnError(error -> System.err.println("Erro no Flux: " + error.getMessage()));
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
