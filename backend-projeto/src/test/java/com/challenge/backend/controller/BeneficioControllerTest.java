package com.challenge.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BeneficioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveListarBeneficios() throws Exception {
        mockMvc.perform(get("/api/beneficios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Beneficio A"));
    }

    @Test
    void deveRetornar422QuandoSaldoInsuficiente() throws Exception {
        String payload = objectMapper.writeValueAsString(new TransferRequestFixture(2L, 1L, "999.00"));

        mockMvc.perform(post("/api/beneficios/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("Saldo insuficiente para transferencia"));
    }

    static class TransferRequestFixture {
        public Long fromId;
        public Long toId;
        public String amount;
        TransferRequestFixture(Long fromId, Long toId, String amount) {
            this.fromId = fromId;
            this.toId = toId;
            this.amount = amount;
        }
    }
}
