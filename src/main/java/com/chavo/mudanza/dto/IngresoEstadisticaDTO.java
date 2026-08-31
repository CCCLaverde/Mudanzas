package com.chavo.mudanza.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record IngresoEstadisticaDTO(
        LocalDate fecha,
        BigDecimal totalIngresos
) {}