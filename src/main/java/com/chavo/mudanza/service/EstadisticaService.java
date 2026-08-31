package com.chavo.mudanza.service;

import com.chavo.mudanza.dto.ColaboradorEstadisticaDTO;
import com.chavo.mudanza.dto.IngresoEstadisticaDTO;
import com.chavo.mudanza.repository.MudanzaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstadisticaService {

    private final MudanzaRepository mudanzaRepository;

    public EstadisticaService(MudanzaRepository mudanzaRepository) {
        this.mudanzaRepository = mudanzaRepository;
    }

    // =====================================================
    // ESTADÍSTICAS DE COLABORADORES
    // =====================================================

    public List<ColaboradorEstadisticaDTO> obtenerEstadisticasColaboradores(
            Integer mes,
            Integer anio) {

        return mudanzaRepository.obtenerEstadisticasColaboradores(
                mes,
                anio
        );
    }

    // =====================================================
    // INGRESOS POR DÍA
    // =====================================================

    public List<IngresoEstadisticaDTO> obtenerIngresosPorSemana(
            LocalDate inicio,
            LocalDate fin) {

        return mudanzaRepository.obtenerIngresosPorSemana(
                inicio,
                fin
        );
    }
}