package com.chavo.mudanza.repository;


import com.chavo.mudanza.entity.Colaborador;
import com.chavo.mudanza.entity.EstadoColaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    // Obtener todos los colaboradores por estado
    List<Colaborador> findByEstado(EstadoColaborador estado);

    // Buscar por nombre (útil para futuras búsquedas)
    List<Colaborador> findByNombreContainingIgnoreCase(String nombre);

}