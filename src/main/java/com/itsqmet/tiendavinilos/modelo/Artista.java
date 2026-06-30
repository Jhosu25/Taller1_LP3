package com.itsqmet.tiendavinilos.modelo;

import jakarta.persistence.*;


@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String pais;

        // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    // Constructores
    public Artista() {
    }

    public Artista(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }
}
