package br.com.example.upafacil.ms_agendamento.presentation.controller;

import br.com.example.upafacil.ms_agendamento.application.usecases.*;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.presentation.dto.UpaDto;
import br.com.example.upafacil.ms_agendamento.presentation.dto.UpaLocationDto;
import br.com.example.upafacil.ms_agendamento.presentation.mapper.UpaDtoMapper;
import br.com.example.upafacil.ms_agendamento.presentation.mapper.UpaLocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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


    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteUpa(@PathVariable("id") Long upaId) {
        return deletUpaUseCase.deleteUpaById(upaId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<UpaDto>> updateUpa (@PathVariable("id") Long upaId, @RequestBody UpaDto upaDto) {
        Mono<UpaDto> upa = updateUpaUseCase.updateUpa(upaId, upaDtoMapper.toDomain(upaDto))
                .map(upaDtoMapper::toDto);

        return upa.map(ResponseEntity::ok);
    }

    @GetMapping("/near-upa")
    public Mono<UpaLocationDto> getNearestUpa(@RequestParam Double latitude, @RequestParam Double longitude) {
        Mono<Upa> upaDomain =  findNearestUpaUseCase.findNearestUpa(latitude, longitude);

        return upaDomain.map(upaLocationMapper::toDto);
    }


}
