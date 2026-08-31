package com.chavo.mudanza.dto;

import com.chavo.mudanza.entity.EstadoMudanza;
import com.chavo.mudanza.entity.EstadoPago;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record MudanzaRequestDTO(

        LocalDate fecha,
        LocalTime hora,

        String lugarRecogida,
        String lugarEntrega,
        String descripcion,

        EstadoMudanza estado,

        ClienteDTO cliente,

        @NotEmpty(message = "Debe seleccionar al menos un colaborador")
        List<Long> colaboradores,

        @NotNull(message = "Debe ingresar el precio de la mudanza")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
        BigDecimal precio,

        EstadoPago estadoPago

) {}