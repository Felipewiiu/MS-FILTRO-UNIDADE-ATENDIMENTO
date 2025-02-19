package br.com.example.upafacil.ms_agendamento.presentation.controller;

import br.com.example.upafacil.ms_agendamento.application.usecases.CreateUpaUseCase;
import br.com.example.upafacil.ms_agendamento.application.usecases.DeletUpaUseCase;
import br.com.example.upafacil.ms_agendamento.application.usecases.FindAllUpasUseCase;
import br.com.example.upafacil.ms_agendamento.application.usecases.FindUpaByIdUseCase;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.presentation.dto.UpaDto;
import br.com.example.upafacil.ms_agendamento.presentation.mapper.UpaDtoMapper;
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


}
