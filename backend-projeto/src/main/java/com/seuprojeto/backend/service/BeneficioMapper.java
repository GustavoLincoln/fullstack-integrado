package com.seuprojeto.backend.service;

import com.seuprojeto.backend.dto.BeneficioRequest;
import com.seuprojeto.backend.dto.BeneficioResponse;
import com.seuprojeto.backend.entity.Beneficio;
import org.springframework.stereotype.Component;

@Component
public class BeneficioMapper {

    public BeneficioResponse toResponse(Beneficio entity) {
        BeneficioResponse response = new BeneficioResponse();
        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setDescricao(entity.getDescricao());
        response.setValor(entity.getValor());
        response.setAtivo(entity.getAtivo());
        response.setVersion(entity.getVersion());
        return response;
    }

    public Beneficio toEntity(BeneficioRequest request) {
        Beneficio entity = new Beneficio();
        updateEntity(entity, request);
        return entity;
    }

    public void updateEntity(Beneficio entity, BeneficioRequest request) {
        entity.setNome(request.getNome());
        entity.setDescricao(request.getDescricao());
        entity.setValor(request.getValor());
        entity.setAtivo(request.getAtivo());
    }
}
