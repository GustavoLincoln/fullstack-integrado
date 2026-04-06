package com.seuprojeto.backend.dto;

import java.math.BigDecimal;

public class TransferDTO {
    private Long fromId;
    private Long toId;
    private BigDecimal valor;

    public Long getFromId() { return fromId; }
    public void setFromId(Long fromId) { this.fromId = fromId; }
    public Long getToId() { return toId; }
    public void setToId(Long toId) { this.toId = toId; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
}
