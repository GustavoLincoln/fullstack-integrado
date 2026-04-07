package com.seuprojeto.backend.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferenciaRequest {
    @NotNull
    private Long fromId;
    @NotNull
    private Long toId;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    public Long getFromId() { return fromId; }
    public void setFromId(Long fromId) { this.fromId = fromId; }
    public Long getToId() { return toId; }
    public void setToId(Long toId) { this.toId = toId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
