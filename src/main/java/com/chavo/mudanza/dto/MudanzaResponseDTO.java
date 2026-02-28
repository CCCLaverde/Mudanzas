package com.chavo.mudanza.dto;


import com.chavo.mudanza.entity.EstadoMudanza;

import java.time.LocalDate;
import java.time.LocalTime;

public record MudanzaResponseDTO(

        Long id,
        LocalDate fecha,
        LocalTime hora,
        String lugarRecogida,
        String lugarEntrega,
        String descripcion,
        EstadoMudanza estado

) {}