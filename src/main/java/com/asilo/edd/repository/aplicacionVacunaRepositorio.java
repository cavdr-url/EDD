package com.asilo.edd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asilo.edd.Model.aplicacionVacunaModel;

import java.util.List;

@Repository
public interface aplicacionVacunaRepositorio extends JpaRepository<aplicacionVacunaModel, Long> {
    // Métodos CRUD de Spring Data JPA ya disponibles
    
    // Método para buscar aplicaciones por mascota
    List<aplicacionVacunaModel> findByMascotaId(Long mascotaId);
    
    // Método para buscar aplicaciones por vacuna
    List<aplicacionVacunaModel> findByVacunaId(Long vacunaId);
}
