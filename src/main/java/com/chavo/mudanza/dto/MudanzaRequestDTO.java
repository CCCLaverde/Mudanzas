package com.chavo.mudanza.dto;

import com.chavo.mudanza.entity.Cliente;
import com.chavo.mudanza.entity.EstadoMudanza;
import jakarta.validation.constraints.NotEmpty;

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
        List<Long> colaboradores

) {}