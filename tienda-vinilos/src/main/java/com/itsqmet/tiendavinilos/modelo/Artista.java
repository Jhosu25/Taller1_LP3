package com.itsqmet.tiendavinilos.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Artista (banda o solista).
 */
@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del artista es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El pais es obligatorio")
    private String pais;


    @OneToMany(mappedBy = "artista")
    private List<Disco> discos = new ArrayList<>();

    public Artista() {
    }

    public Artista(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public List<Disco> getDiscos() { return discos; }
    public void setDiscos(List<Disco> discos) { this.discos = discos; }
}
