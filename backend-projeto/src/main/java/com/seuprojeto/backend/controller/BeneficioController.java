package com.seuprojeto.backend.controller;


import com.seuprojeto.backend.dto.BeneficioRequest;
import com.seuprojeto.backend.dto.BeneficioResponse;
import com.seuprojeto.backend.dto.TransferenciaRequest;
import com.seuprojeto.backend.entity.Beneficio;
import com.seuprojeto.backend.service.BeneficioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/beneficios")
@Validated
public class BeneficioController {

    private final BeneficioService service;

    public BeneficioController(BeneficioService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lista todos os beneficios")
    public List<BeneficioResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca beneficio por id")
    public BeneficioResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um beneficio")
    public BeneficioResponse criar(@RequestBody @Valid BeneficioRequest request) {
        return service.criar(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um beneficio")
    public BeneficioResponse atualizar(@PathVariable Long id, @RequestBody @Valid BeneficioRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove um beneficio")
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }

    @PostMapping("/transferencias")
    @Operation(summary = "Executa transferencia entre beneficios")
    public ResponseEntity<Void> transferir(@RequestBody @Valid TransferenciaRequest request) {
        service.transferir(request);
        return ResponseEntity.ok().build();
    }
}
