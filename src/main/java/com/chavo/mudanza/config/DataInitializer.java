package com.chavo.mudanza.config;

import com.chavo.mudanza.entity.Rol;
import com.chavo.mudanza.entity.Usuario;
import com.chavo.mudanza.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner crearUsuarioInicial(UsuarioRepository usuarioRepository,
                                          PasswordEncoder passwordEncoder) {

        return args -> {

            if (usuarioRepository.findByUsername("admin").isEmpty()) {

                Usuario admin = new Usuario(
                        "admin",
                        passwordEncoder.encode("Admin1234"),
                        Rol.ADMIN
                );

                usuarioRepository.save(admin);

                System.out.println("=================================");
                System.out.println("Usuario ADMIN creado");
                System.out.println("Usuario: admin");
                System.out.println("=================================");
            }
        };
    }
}