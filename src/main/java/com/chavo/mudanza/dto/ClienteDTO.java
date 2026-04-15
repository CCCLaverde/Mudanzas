package com.chavo.mudanza.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String telefono,
        @NotBlank
        String email
) {
}
