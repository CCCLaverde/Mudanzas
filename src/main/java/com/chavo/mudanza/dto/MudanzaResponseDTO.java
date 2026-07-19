package com.chavo.mudanza.dto;

import com.chavo.mudanza.entity.EstadoMudanza;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record MudanzaResponseDTO(

        Long id,
        LocalDate fecha,
        LocalTime hora,

        @NotBlank
        String lugarRecogida,

        @NotBlank
        String lugarEntrega,

        @NotBlank
        String descripcion,

        EstadoMudanza estado,

        String nombreCliente,
        String telefonoCliente,

        List<ColaboradorDTO> colaboradores

) {}