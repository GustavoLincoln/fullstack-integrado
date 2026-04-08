package com.seuprojeto.backend.repository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.seuprojeto.backend.entity.Beneficio;

@DataJpaTest
class BeneficioRepositoryTest {

    @Autowired
    private BeneficioRepository repository;

    @Test
    void deveSalvarBeneficio() {

        Beneficio b = new Beneficio();
        b.setNome("Teste");
        b.setValor(new BigDecimal("100"));
        b.setAtivo(true);

        Beneficio saved = repository.save(b);

        assertNotNull(saved.getId());
    }
}
