package com.seuprojeto.backend.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seuprojeto.backend.dto.TransferenciaRequest;
import com.seuprojeto.backend.service.BeneficioService;

@WebMvcTest(BeneficioController.class)
class BeneficioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeneficioService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRealizarTransferencia() throws Exception {

        TransferenciaRequest req = new TransferenciaRequest();
        req.setFromId(1L);
        req.setToId(2L);
        req.setValor(new BigDecimal("50"));

        doNothing().when(service).transferir(any());

        mockMvc.perform(post("/api/beneficios/transferencias")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }
}
