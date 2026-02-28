package com.chavo.mudanza.repository;

import com.chavo.mudanza.entity.EstadoMudanza;
import com.chavo.mudanza.entity.Mudanza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MudanzaRepository extends JpaRepository<Mudanza, Long> {

    // 🔹 Buscar por fecha
    List<Mudanza> findByFecha(LocalDate fecha);

    // 🔹 Buscar por estado
    List<Mudanza> findByEstado(EstadoMudanza estado);

    // 🔹 Buscar por fecha y estado
    List<Mudanza> findByFechaAndEstado(LocalDate fecha, EstadoMudanza estado);
}