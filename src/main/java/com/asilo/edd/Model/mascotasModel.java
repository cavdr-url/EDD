package com.asilo.edd.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "perros")
public class mascotasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;               // Nombre del perrito
    private String codigo;               // Código o identificación
    private String raza;                 // Raza
    private Integer edadAproximada;      // Edad aproximada en meses
    private java.time.LocalDate fechaIngreso; // Fecha de ingreso al refugio
    private String observaciones;        // Notas importantes

    // Constructor vacío (obligatorio para JPA)
    public mascotasModel() { }

    // Constructor con parámetros
    public mascotasModel(String nombre, String codigo, String raza,
               Integer edadAproximada, java.time.LocalDate fechaIngreso,
               String observaciones) {

        this.nombre = nombre;
        this.codigo = codigo;
        this.raza = raza;
        this.edadAproximada = edadAproximada;
        this.fechaIngreso = fechaIngreso;
        this.observaciones = observaciones;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public Integer getEdadAproximada() { return edadAproximada; }
    public void setEdadAproximada(Integer edadAproximada) { this.edadAproximada = edadAproximada; }

    public java.time.LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(java.time.LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}