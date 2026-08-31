package com.chavo.mudanza.repository;

import com.chavo.mudanza.dto.IngresoEstadisticaDTO;
import com.chavo.mudanza.entity.EstadoMudanza;
import com.chavo.mudanza.entity.Mudanza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import com.chavo.mudanza.dto.ColaboradorEstadisticaDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("""
    SELECT new com.chavo.mudanza.dto.ColaboradorEstadisticaDTO(
        c.id,
        c.nombre,
        COUNT(m.id)
    )
    FROM Mudanza m
    JOIN m.colaboradores c
    WHERE EXTRACT(MONTH FROM m.fecha) = :mes
      AND EXTRACT(YEAR FROM m.fecha) = :anio
    GROUP BY c.id, c.nombre
    ORDER BY COUNT(m.id) DESC
""")
    List<ColaboradorEstadisticaDTO> obtenerEstadisticasColaboradores(
            @Param("mes") Integer mes,
            @Param("anio") Integer anio
    );

    @Query("""
    SELECT new com.chavo.mudanza.dto.IngresoEstadisticaDTO(
        m.fecha,
        COALESCE(SUM(m.precio), 0)
    )
    FROM Mudanza m
    WHERE m.estadoPago = com.chavo.mudanza.entity.EstadoPago.PAGADO
    GROUP BY m.fecha
    ORDER BY m.fecha
""")
    List<IngresoEstadisticaDTO> obtenerIngresosPorSemana(
            LocalDate inicio,
            LocalDate fin
    );


}