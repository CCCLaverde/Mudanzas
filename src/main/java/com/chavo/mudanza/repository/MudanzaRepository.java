package com.chavo.mudanza.repository;

import com.chavo.mudanza.entity.EstadoMudanza;
import com.chavo.mudanza.entity.Mudanza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MudanzaRepository extends JpaRepository<Mudanza, Long> {

    // ==========================
// TODAS ORDENADAS
// ==========================

    List<Mudanza> findAllByOrderByFechaDescHoraDesc();

    // ==========================
    // FECHA
    // ==========================

    List<Mudanza> findByFecha(LocalDate fecha);

    List<Mudanza> findByFechaBetween(LocalDate inicio, LocalDate fin);

    // ==========================
    // ESTADO
    // ==========================

    List<Mudanza> findByEstado(EstadoMudanza estado);

    List<Mudanza> findByFechaAndEstado(LocalDate fecha, EstadoMudanza estado);

    // ==========================
    // COLABORADOR
    // ==========================

    List<Mudanza> findByColaboradores_Id(Long colaboradorId);

    List<Mudanza> findByFechaAndColaboradores_Id(LocalDate fecha, Long colaboradorId);

    List<Mudanza> findByFechaBetweenAndColaboradores_Id(
            LocalDate inicio,
            LocalDate fin,
            Long colaboradorId);



}