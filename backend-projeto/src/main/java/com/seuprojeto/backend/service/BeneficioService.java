package com.seuprojeto.backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.seuprojeto.backend.entity.Beneficio;
import com.seuprojeto.backend.exception.BusinessException;
import com.seuprojeto.backend.repository.BeneficioRepository;

@Service
public class BeneficioService {

    private final BeneficioRepository repository;

    public BeneficioService(BeneficioRepository repository) {
        this.repository = repository;
    }

    public List<Beneficio> listar() {
        return repository.findAll();
    }

    public Beneficio criar(Beneficio b) {
        return repository.save(b);
    }

    public void transferir(Long fromId, Long toId, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Valor inválido");
        }

    }
}
