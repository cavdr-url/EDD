package com.asilo.edd.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;

@Entity
@Table(name = "perros")
public class mascotasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;               // Nombre del perrito
    private String raza;                 // Raza
    private Integer edadAproximada;      // Edad aproximada en meses
    private java.time.LocalDate fechaIngreso; // Fecha de ingreso al refugio
    private String observaciones;        // Notas importantes
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String imagen;                // Imagen de la mascota (Base64)
    
    private String estado;               // Estado: disponible / adoptado
    private java.time.LocalDate fechaAdopcion; // Fecha de adopción
    private String adoptante;            // Nombre del adoptante

    // Constructor vacío (obligatorio para JPA)
    public mascotasModel() { 
        this.estado = "disponible"; // Valor por defecto
    }

    // Constructor con parámetros
    public mascotasModel(String nombre, String raza,
               Integer edadAproximada, java.time.LocalDate fechaIngreso,
               String observaciones, String imagen) {

        this.nombre = nombre;
        this.raza = raza;
        this.edadAproximada = edadAproximada;
        this.fechaIngreso = fechaIngreso;
        this.observaciones = observaciones;
        this.imagen = imagen;
        this.estado = "disponible";
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public Integer getEdadAproximada() { return edadAproximada; }
    public void setEdadAproximada(Integer edadAproximada) { this.edadAproximada = edadAproximada; }

    public java.time.LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(java.time.LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public java.time.LocalDate getFechaAdopcion() { return fechaAdopcion; }
    public void setFechaAdopcion(java.time.LocalDate fechaAdopcion) { this.fechaAdopcion = fechaAdopcion; }

    public String getAdoptante() { return adoptante; }
    public void setAdoptante(String adoptante) { this.adoptante = adoptante; }
}
