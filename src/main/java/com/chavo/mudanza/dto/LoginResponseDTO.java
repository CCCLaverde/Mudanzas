package com.chavo.mudanza.dto;

public record LoginResponseDTO(
        String token,
        String username,
        String rol
) {
}