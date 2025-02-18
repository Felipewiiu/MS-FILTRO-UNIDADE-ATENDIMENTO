package br.com.example.upafacil.ms_agendamento.presentation.controller;

import br.com.example.upafacil.ms_agendamento.application.usecases.CreateUpaUseCase;
import br.com.example.upafacil.ms_agendamento.domain.entities.Upa;
import br.com.example.upafacil.ms_agendamento.presentation.dto.UpaDto;
import br.com.example.upafacil.ms_agendamento.presentation.mapper.UpaDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upa")
public class UpaController {

    private final CreateUpaUseCase createUpaUseCase;
    private final UpaDtoMapper upaDtoMapper;

    @PostMapping("/create")
    public Mono<ResponseEntity<UpaDto>> createUpa(@RequestBody UpaDto upaDto) {
        Mono<Upa> upa = createUpaUseCase.createUpa(upaDtoMapper.toDomain(upaDto));

        return upa.map(upas -> ResponseEntity.ok(upaDtoMapper.toDto(upas)));

    }


}
