package com.seuprojeto.backend.service;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.seuprojeto.backend.dto.TransferenciaRequest;
import com.seuprojeto.backend.entity.Beneficio;
import com.seuprojeto.backend.exception.BusinessException;
import com.seuprojeto.backend.repository.BeneficioRepository;

class BeneficioServiceTest {

    private final BeneficioRepository repository = mock(BeneficioRepository.class);
    private final BeneficioService service = new BeneficioService(repository, null, null);

    @Test
    void deveTransferirComSucesso() {

        Beneficio from = new Beneficio();
        from.setId(1L);
        from.setValor(new BigDecimal("100"));

        Beneficio to = new Beneficio();
        to.setId(2L);
        to.setValor(new BigDecimal("50"));

        when(repository.findById(1L)).thenReturn(Optional.of(from));
        when(repository.findById(2L)).thenReturn(Optional.of(to));

        TransferenciaRequest req = new TransferenciaRequest();
        req.setFromId(1L);
        req.setToId(2L);
        req.setValor(new BigDecimal("30"));

        service.transferir(req);

        assertEquals(new BigDecimal("70"), from.getValor());
        assertEquals(new BigDecimal("80"), to.getValor());
    }

    @Test
    void deveFalharComSaldoInsuficiente() {

        Beneficio from = new Beneficio();
        from.setId(1L);
        from.setValor(new BigDecimal("10"));

        Beneficio to = new Beneficio();
        to.setId(2L);
        to.setValor(new BigDecimal("50"));

        when(repository.findById(1L)).thenReturn(Optional.of(from));
        when(repository.findById(2L)).thenReturn(Optional.of(to));

        TransferenciaRequest req = new TransferenciaRequest();
        req.setFromId(1L);
        req.setToId(2L);
        req.setValor(new BigDecimal("100"));

        assertThrows(BusinessException.class, () -> {
            service.transferir(req);
        });
    }
}
