package com.chavo.mudanza.dto;

import com.chavo.mudanza.entity.EstadoMudanza;
import com.chavo.mudanza.entity.EstadoPago;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record MudanzaResponseDTO(

        Long id,

        LocalDate fecha,
        LocalTime hora,

        String lugarRecogida,
        String lugarEntrega,
        String descripcion,

        EstadoMudanza estado,

        String nombreCliente,
        String telefonoCliente,

        List<ColaboradorDTO> colaboradores,

        BigDecimal precio,

        EstadoPago estadoPago

) {}