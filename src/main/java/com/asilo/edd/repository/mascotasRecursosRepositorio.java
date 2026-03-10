package com.asilo.edd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asilo.edd.Model.mascotasModel;
import java.util.List;

@Repository
public interface mascotasRecursosRepositorio extends JpaRepository<mascotasModel, Long> {
    // Métodos CRUD de Spring Data JPA ya disponibles
    List<mascotasModel> findByEstado(String estado);
    List<mascotasModel> findByEstadoNot(String estado);
}
