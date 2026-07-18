package com.chavo.mudanza.service;

import com.chavo.mudanza.dto.ColaboradorDTO;
import com.chavo.mudanza.entity.Colaborador;
import com.chavo.mudanza.entity.EstadoColaborador;
import com.chavo.mudanza.mapper.ColaboradorMapper;
import com.chavo.mudanza.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    private final ColaboradorRepository repository;
    private final ColaboradorMapper mapper;

    public ColaboradorService(ColaboradorRepository repository,
                              ColaboradorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // 🔹 Crear colaborador
    public ColaboradorDTO crear(ColaboradorDTO dto) {

        Colaborador colaborador = mapper.toEntity(dto);

        // Si no envían estado, queda ACTIVO por defecto
        if (colaborador.getEstado() == null) {
            colaborador.setEstado(EstadoColaborador.ACTIVO);
        }

        Colaborador guardado = repository.save(colaborador);

        return mapper.toDTO(guardado);
    }

    // 🔹 Obtener todos
    public List<ColaboradorDTO> obtenerTodos() {

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // 🔹 Obtener por ID
    public ColaboradorDTO obtenerPorId(Long id) {

        Colaborador colaborador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));

        return mapper.toDTO(colaborador);
    }

    // 🔹 Obtener por estado
    public List<ColaboradorDTO> obtenerPorEstado(EstadoColaborador estado) {

        return repository.findByEstado(estado)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // 🔹 Buscar por nombre
    public List<ColaboradorDTO> buscarPorNombre(String nombre) {

        return repository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // 🔹 Actualizar
    public ColaboradorDTO actualizar(Long id, ColaboradorDTO dto) {

        Colaborador colaborador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));

        colaborador.setNombre(dto.nombre());
        colaborador.setEstado(dto.estado());

        Colaborador actualizado = repository.save(colaborador);

        return mapper.toDTO(actualizado);
    }

    // 🔹 Eliminar
    public void eliminar(Long id) {

        Colaborador colaborador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));

        repository.delete(colaborador);
    }

    // 🔹 Desactivar colaborador
    public void desactivar(Long id) {

        Colaborador colaborador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));

        colaborador.setEstado(EstadoColaborador.INACTIVO);

        repository.save(colaborador);
    }

    // 🔹 Activar colaborador
    public void activar(Long id) {

        Colaborador colaborador = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));

        colaborador.setEstado(EstadoColaborador.ACTIVO);

        repository.save(colaborador);
    }
}