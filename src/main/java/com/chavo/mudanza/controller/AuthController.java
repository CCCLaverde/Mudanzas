package com.chavo.mudanza.controller;

import com.chavo.mudanza.dto.LoginRequestDTO;
import com.chavo.mudanza.dto.LoginResponseDTO;
import com.chavo.mudanza.entity.Usuario;
import com.chavo.mudanza.security.JwtService;
import com.chavo.mudanza.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(
            AuthService authService,
            JwtService jwtService
    ) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO request
    ) {

        Usuario usuario = authService.autenticar(request);

        String token = jwtService.generarToken(
                usuario.getUsername(),
                usuario.getRol().name()
        );

        return new LoginResponseDTO(
                token,
                usuario.getUsername(),
                usuario.getRol().name()
        );
    }
}