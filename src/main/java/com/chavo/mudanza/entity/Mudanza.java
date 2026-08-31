package com.chavo.mudanza.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    // 💰 Valor total de la mudanza
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @Enumerated(EnumType.STRING)
    private EstadoMudanza estado;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "mudanza_colaboradores",
            joinColumns = @JoinColumn(name = "mudanza_id"),
            inverseJoinColumns = @JoinColumn(name = "colaborador_id")
    )
    private List<Colaborador> colaboradores;

    // ==========================
    // Constructor vacío
    // ==========================

    public Mudanza() {
    }

    // ==========================
    // Constructor completo
    // ==========================

    public Mudanza(
            Long id,
            LocalDate fecha,
            LocalTime hora,
            String lugarRecogida,
            String lugarEntrega,
            String descripcion,
            BigDecimal precio,
            EstadoMudanza estado,
            EstadoPago estadoPago,
            Cliente cliente,
            List<Colaborador> colaboradores
    ) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.lugarRecogida = lugarRecogida;
        this.lugarEntrega = lugarEntrega;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.estadoPago = estadoPago;
        this.cliente = cliente;
        this.colaboradores = colaboradores;
    }

    // ==========================
    // Getters & Setters
    // ==========================

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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public EstadoMudanza getEstado() {
        return estado;
    }

    public void setEstado(EstadoMudanza estado) {
        this.estado = estado;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }
}