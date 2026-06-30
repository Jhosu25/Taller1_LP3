package com.itsqmet.tiendavinilos.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Genero musical (Rock, Jazz, Pop, etc.)
 */
@Entity
@Table(name = "generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del genero es obligatorio")
    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @ManyToMany(mappedBy = "generos")
    private Set<Disco> discos = new HashSet<>();

    public Genero() {
    }

    public Genero(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Set<Disco> getDiscos() { return discos; }
    public void setDiscos(Set<Disco> discos) { this.discos = discos; }
}
