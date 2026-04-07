package com.challenge.backend.service;

import com.challenge.backend.dto.TransferenciaRequest;
import com.challenge.backend.entity.Beneficio;
import com.challenge.backend.exception.BusinessException;
import com.challenge.backend.repository.BeneficioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class BeneficioServiceTest {

    @Autowired
    private BeneficioService service;

    @Autowired
    private BeneficioRepository repository;

    @Test
    void deveTransferirComSucesso() {
        TransferenciaRequest request = new TransferenciaRequest();
        request.setFromId(1L);
        request.setToId(2L);
        request.setAmount(new BigDecimal("100.00"));

        service.transferir(request);

        Beneficio origem = repository.findById(1L).orElseThrow(RuntimeException::new);
        Beneficio destino = repository.findById(2L).orElseThrow(RuntimeException::new);

        assertEquals(new BigDecimal("900.00"), origem.getValor());
        assertEquals(new BigDecimal("600.00"), destino.getValor());
    }

    @Test
    void deveFalharQuandoSaldoForInsuficiente() {
        TransferenciaRequest request = new TransferenciaRequest();
        request.setFromId(2L);
        request.setToId(1L);
        request.setAmount(new BigDecimal("999.00"));

        assertThrows(BusinessException.class, () -> service.transferir(request));
    }
}
