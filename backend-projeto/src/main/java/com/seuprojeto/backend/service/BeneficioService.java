package com.seuprojeto.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seuprojeto.backend.dto.BeneficioRequest;
import com.seuprojeto.backend.dto.BeneficioResponse;
import com.seuprojeto.backend.dto.TransferenciaRequest;
import com.seuprojeto.backend.entity.Beneficio;
import com.seuprojeto.backend.exception.BusinessException;
import com.seuprojeto.backend.exception.ResourceNotFoundException;
import com.seuprojeto.backend.repository.BeneficioRepository;

@Service
public class BeneficioService {

    private final BeneficioRepository repository;
    private final BeneficioMapper mapper;
    private final EntityManager entityManager;

    public BeneficioService(BeneficioRepository repository, BeneficioMapper mapper, EntityManager entityManager) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    public BeneficioService(BeneficioRepository repository) {
        this.repository = repository;
        this.mapper = null;
        this.entityManager = null;
    }

    @Transactional(readOnly = true)
    public List<BeneficioResponse> listar() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BeneficioResponse buscarPorId(Long id) {
        return mapper.toResponse(findById(id));
    }

    @Transactional
    public BeneficioResponse criar(BeneficioRequest request) {
        Beneficio saved = repository.save(mapper.toEntity(request));
        return mapper.toResponse(saved);
    }

    @Transactional
    public BeneficioResponse atualizar(Long id, BeneficioRequest request) {
        Beneficio entity = findById(id);
        mapper.updateEntity(entity, request);
        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public void remover(Long id) {
        Beneficio entity = findById(id);
        repository.delete(entity);
    }

    @Transactional
    public void transferir(TransferenciaRequest request) {
        if (request.getFromId().equals(request.getToId())) {
            throw new IllegalArgumentException("Origem e destino nao podem ser iguais");
        }

        if (entityManager == null) {

            Beneficio origem = repository.findById(request.getFromId())
                    .orElseThrow(() -> new ResourceNotFoundException("Beneficio de origem nao encontrado"));

            Beneficio destino = repository.findById(request.getToId())
                    .orElseThrow(() -> new ResourceNotFoundException("Beneficio de destino nao encontrado"));

            if (origem.getValor().compareTo(request.getValor()) < 0) {
                throw new BusinessException("Saldo insuficiente para transferencia");
            }

            origem.setValor(origem.getValor().subtract(request.getValor()));
            destino.setValor(destino.getValor().add(request.getValor()));

            repository.save(origem);
            repository.save(destino);

            return;
        }

        Beneficio origem = entityManager.find(
                Beneficio.class,
                request.getFromId(),
                LockModeType.PESSIMISTIC_WRITE
        );

        Beneficio destino = entityManager.find(
                Beneficio.class,
                request.getToId(),
                LockModeType.PESSIMISTIC_WRITE
        );

        if (origem == null) {
            throw new ResourceNotFoundException("Beneficio de origem nao encontrado");
        }
        if (destino == null) {
            throw new ResourceNotFoundException("Beneficio de destino nao encontrado");
        }
        if (origem.getValor().compareTo(request.getValor())
                < 0) {
            throw new BusinessException("Saldo insuficiente para transferencia");
        }

        origem.setValor(origem.getValor().subtract(request.getValor()));
        destino.setValor(destino.getValor().add(request.getValor()));

        entityManager.merge(origem);
        entityManager.merge(destino);
        entityManager.flush();
    }

    private Beneficio findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Beneficio nao encontrado: " + id));
    }
}
