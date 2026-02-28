package com.chavo.mudanza.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "mudanzas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mudanza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false, length = 150)
    private String lugarRecogida;

    @Column(nullable = false, length = 150)
    private String lugarEntrega;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoMudanza estado;
}