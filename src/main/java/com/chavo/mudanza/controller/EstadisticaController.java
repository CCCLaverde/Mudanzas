package com.chavo.mudanza.controller;

import com.chavo.mudanza.dto.ColaboradorEstadisticaDTO;
import com.chavo.mudanza.dto.IngresoEstadisticaDTO;
import com.chavo.mudanza.service.EstadisticaService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/estadisticas")
@CrossOrigin(origins = "*")
public class EstadisticaController {

    private final EstadisticaService service;

    public EstadisticaController(EstadisticaService service) {
        this.service = service;
    }

    // ==========================
    // ESTADÍSTICAS COLABORADORES
    // ==========================

    @GetMapping("/colaboradores")
    public List<ColaboradorEstadisticaDTO> obtenerEstadisticasColaboradores(
            @RequestParam Integer mes,
            @RequestParam Integer anio
    ) {

        return service.obtenerEstadisticasColaboradores(
                mes,
                anio
        );
    }

    // ==========================
    // INGRESOS POR SEMANA
    // ==========================

    @GetMapping("/ingresos")
    public List<IngresoEstadisticaDTO> obtenerIngresosPorSemana(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin
    ) {

        return service.obtenerIngresosPorSemana(
                fechaInicio,
                fechaFin
        );
    }
}