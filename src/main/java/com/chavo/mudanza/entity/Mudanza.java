package com.chavo.mudanza.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "mudanzas")
public class Mudanza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private LocalTime hora;

    private String lugarRecogida;

    private String lugarEntrega;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoMudanza estado;

    // 🔹 Constructor vacío
    public Mudanza() {
    }

    // 🔹 Constructor completo
    public Mudanza(Long id, LocalDate fecha, LocalTime hora,
                   String lugarRecogida, String lugarEntrega,
                   String descripcion, EstadoMudanza estado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.lugarRecogida = lugarRecogida;
        this.lugarEntrega = lugarEntrega;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // 🔹 Getters y Setters

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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getLugarRecogida() {
        return lugarRecogida;
    }

    public void setLugarRecogida(String lugarRecogida) {
        this.lugarRecogida = lugarRecogida;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoMudanza getEstado() {
        return estado;
    }

    public void setEstado(EstadoMudanza estado) {
        this.estado = estado;
    }
}