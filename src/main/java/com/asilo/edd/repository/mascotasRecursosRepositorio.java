package com.asilo.edd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asilo.edd.Model.mascotasModel;

@Repository
public interface mascotasRecursosRepositorio extends JpaRepository<mascotasModel, Long> {
    // Métodos CRUD de Spring Data JPA ya disponibles
}
