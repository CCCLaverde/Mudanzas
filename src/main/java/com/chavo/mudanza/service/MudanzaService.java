package com.chavo.mudanza.service;

import com.chavo.mudanza.mapper.MudanzaMapper;
import com.chavo.mudanza.dto.MudanzaRequestDTO;
import com.chavo.mudanza.dto.MudanzaResponseDTO;
import com.chavo.mudanza.entity.Cliente;
import com.chavo.mudanza.entity.EstadoMudanza;
import com.chavo.mudanza.entity.Mudanza;
import com.chavo.mudanza.repository.ClienteRepository;
import com.chavo.mudanza.repository.MudanzaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MudanzaService {

    private final MudanzaRepository repository;
    private final MudanzaMapper mapper;
    private final ClienteRepository clienteRepository;

    public MudanzaService(MudanzaRepository repository,
                          MudanzaMapper mapper,
                          ClienteRepository clienteRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
    }

    // 🔹 Crear mudanza
    public MudanzaResponseDTO crear(MudanzaRequestDTO dto) {

        Mudanza mudanza = mapper.toEntity(dto);

        // Estado por defecto si viene null
        if (mudanza.getEstado() == null) {
            mudanza.setEstado(EstadoMudanza.PENDIENTE);
        }

        Cliente cliente;

        var clienteDTO = dto.cliente();

        // Opción PRO: evitar duplicados por teléfono
        cliente = clienteRepository.findByTelefono(clienteDTO.telefono())
                .orElseGet(() -> {
                    Cliente nuevo = new Cliente();
                    nuevo.setNombre(clienteDTO.nombre());
                    nuevo.setTelefono(clienteDTO.telefono());
                    nuevo.setEmail(clienteDTO.email());
                    return clienteRepository.save(nuevo);
                });

        // 🔥 Asociar cliente a mudanza
        mudanza.setCliente(cliente);

        Mudanza guardada = repository.save(mudanza);

        return mapper.toResponseDTO(guardada);
    }

    // 🔹 Obtener todas
    public List<MudanzaResponseDTO> obtenerTodas() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // 🔹 Obtener por ID
    public MudanzaResponseDTO obtenerPorId(Long id) {

        Mudanza mudanza = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mudanza no encontrada"));

        return mapper.toResponseDTO(mudanza);
    }

    // 🔹 Obtener por fecha
    public List<MudanzaResponseDTO> obtenerPorFecha(LocalDate fecha) {
        return repository.findByFecha(fecha)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // 🔹 Obtener por estado
    public List<MudanzaResponseDTO> obtenerPorEstado(EstadoMudanza estado) {
        return repository.findByEstado(estado)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // 🔹 Actualizar mudanza
    public MudanzaResponseDTO actualizar(Long id, MudanzaRequestDTO dto) {

        Mudanza existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mudanza no encontrada"));

        existente.setFecha(dto.fecha());
        existente.setHora(dto.hora());
        existente.setLugarRecogida(dto.lugarRecogida());
        existente.setLugarEntrega(dto.lugarEntrega());
        existente.setDescripcion(dto.descripcion());
        existente.setEstado(dto.estado());

        Mudanza actualizada = repository.save(existente);

        return mapper.toResponseDTO(actualizada);
    }

    // 🔹 Eliminar mudanza
    public void eliminar(Long id) {

        Mudanza existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mudanza no encontrada"));

        repository.delete(existente);
    }
}