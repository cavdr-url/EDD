package com.asilo.edd.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "aplicaciones_vacunas")
public class aplicacionVacunaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mascotaId;                // Clave Foránea lógico a mascotasModel.id (Tomando ejemplo la db de pyHorario)
    private Long vacunaId;                 // Clave Foránea lógico a Vacuna.id (Tomando ejemplo la db de pyHorario)

    private java.time.LocalDate fechaAplicacion;
    private java.time.LocalDate proximaFechaRefuerzo; // Opcional
    private String observaciones;                      // Opcional

    // Constructor vacío (obligatorio para JPA)
    public aplicacionVacunaModel() { }

    // Constructor con parámetros
    public aplicacionVacunaModel(Long mascotaId, Long vacunaId,
                            java.time.LocalDate fechaAplicacion,
                            java.time.LocalDate proximaFechaRefuerzo,
                            String observaciones) {
        this.mascotaId = mascotaId;
        this.vacunaId = vacunaId;
        this.fechaAplicacion = fechaAplicacion;
        this.proximaFechaRefuerzo = proximaFechaRefuerzo;
        this.observaciones = observaciones;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMascotaId() { return mascotaId; }
    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }

    public Long getVacunaId() { return vacunaId; }
    public void setVacunaId(Long vacunaId) { this.vacunaId = vacunaId; }

    public java.time.LocalDate getFechaAplicacion() { return fechaAplicacion; }
    public void setFechaAplicacion(java.time.LocalDate fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }

    public java.time.LocalDate getProximaFechaRefuerzo() { return proximaFechaRefuerzo; }
    public void setProximaFechaRefuerzo(java.time.LocalDate proximaFechaRefuerzo) { this.proximaFechaRefuerzo = proximaFechaRefuerzo; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}

