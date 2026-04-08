package com.seuprojeto.backend.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class TransferenciaRequest {

    @NotNull
    private Long fromId;
    @NotNull
    private Long toId;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal valor;

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
