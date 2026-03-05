package com.asilo.edd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asilo.edd.Model.vacunasModel;

@Repository
public interface vacunasRepositorio extends JpaRepository<vacunasModel, Long> {
    // Métodos CRUD de Spring Data JPA ya disponibles
}
