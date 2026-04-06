package com.seuprojeto.backend.controller;

import com.seuprojeto.backend.dto.TransferDTO;
import com.seuprojeto.backend.entity.Beneficio;
import com.seuprojeto.backend.service.BeneficioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/beneficios")
public class BeneficioController {

    private final BeneficioService service;

    public BeneficioController(BeneficioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Beneficio> listar() {
        return service.listar();
    }

    @PostMapping
    public Beneficio criar(@RequestBody Beneficio b) {
        return service.criar(b);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferir(@RequestBody TransferDTO dto) {
        service.transferir(dto.getFromId(), dto.getToId(), dto.getValor());
        return ResponseEntity.ok("Transferência realizada");
    }
}
