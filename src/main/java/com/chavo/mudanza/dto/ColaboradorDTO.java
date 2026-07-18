package com.chavo.mudanza.dto;

import com.chavo.mudanza.entity.EstadoColaborador;
import jakarta.validation.constraints.NotBlank;

public record ColaboradorDTO(

        Long id,

        @NotBlank
        String nombre,

        EstadoColaborador estado

) {
}