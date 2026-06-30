package com.itsqmet.tiendavinilos.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private Integer cantidad;

        // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    //  Relacion: muchas ventas -> un cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Relacion: muchas ventas -> un disco
    @ManyToOne
    @JoinColumn(name = "disco_id")
    private Disco disco;

    // Constructores
    public Venta() {
    }

    // Calcula el total de la venta (precio del disco * cantidad)
    public Double getTotal() {
        if (disco != null && disco.getPrecio() != null && cantidad != null) {
            return disco.getPrecio() * cantidad;
        }
        return 0.0;
    }


}
