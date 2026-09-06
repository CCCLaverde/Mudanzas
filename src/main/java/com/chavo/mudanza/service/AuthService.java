package com.chavo.mudanza.service;

import com.chavo.mudanza.dto.LoginRequestDTO;
import com.chavo.mudanza.entity.Usuario;
import com.chavo.mudanza.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario autenticar(LoginRequestDTO request) {

        Usuario usuario = usuarioRepository
                .findByUsername(request.username())
                .orElseThrow(() ->
                        new RuntimeException("Usuario o contraseña incorrectos")
                );

        if (!passwordEncoder.matches(
                request.password(),
                usuario.getPassword()
        )) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        return usuario;
    }
}