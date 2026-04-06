package com.projetoejb.ejb.service;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.projetoejb.ejb.entity.Beneficio;

@Stateless
public class BeneficiosEjbService {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Transactional
    public void transfer(Long fromId, Long toId, BigDecimal amount) {

        if (fromId.equals(toId)) {
            throw new RuntimeException("Mesma conta");
        }

        Beneficio from = em.find(Beneficio.class, fromId, LockModeType.OPTIMISTIC);
        Beneficio to = em.find(Beneficio.class, toId, LockModeType.OPTIMISTIC);

        if (from == null || to == null) {
            throw new RuntimeException("Conta não encontrada");
        }

        if (from.getValor().compareTo(amount) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        from.setValor(from.getValor().subtract(amount));
        to.setValor(to.getValor().add(amount));
    }
}
