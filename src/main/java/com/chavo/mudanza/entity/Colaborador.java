package com.chavo.mudanza.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "colaboradores")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoColaborador estado;

    // 🔹 Constructor vacío
    public Colaborador() {
    }

    // 🔹 Constructor completo
    public Colaborador(Long id, String nombre, EstadoColaborador estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    // 🔹 Getters y Setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoColaborador getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(EstadoColaborador estado) {
        this.estado = estado;
    }
}