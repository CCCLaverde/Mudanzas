package com.chavo.mudanza.service;

import com.chavo.mudanza.dto.MudanzaRequestDTO;
import com.chavo.mudanza.dto.MudanzaResponseDTO;
import com.chavo.mudanza.entity.Cliente;
import com.chavo.mudanza.entity.Colaborador;
import com.chavo.mudanza.entity.EstadoMudanza;
import com.chavo.mudanza.entity.Mudanza;
import com.chavo.mudanza.mapper.MudanzaMapper;
import com.chavo.mudanza.repository.ClienteRepository;
import com.chavo.mudanza.repository.ColaboradorRepository;
import com.chavo.mudanza.repository.MudanzaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MudanzaService {

    private final MudanzaRepository repository;
    private final MudanzaMapper mapper;
    private final ClienteRepository clienteRepository;
    private final ColaboradorRepository colaboradorRepository;

    public MudanzaService(MudanzaRepository repository,
                          MudanzaMapper mapper,
                          ClienteRepository clienteRepository,
                          ColaboradorRepository colaboradorRepository) {

        this.repository = repository;
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    // 🔹 Crear mudanza
    public MudanzaResponseDTO crear(MudanzaRequestDTO dto) {

        Mudanza mudanza = mapper.toEntity(dto);

        // Estado por defecto
        if (mudanza.getEstado() == null) {
            mudanza.setEstado(EstadoMudanza.PENDIENTE);
        }

        // ==========================
        // CLIENTE
        // ==========================

        var clienteDTO = dto.cliente();

        Cliente cliente = clienteRepository.findByTelefono(clienteDTO.telefono())
                .orElseGet(() -> {
                    Cliente nuevo = new Cliente();
                    nuevo.setNombre(clienteDTO.nombre());
                    nuevo.setTelefono(clienteDTO.telefono());
                    nuevo.setEmail(clienteDTO.email());
                    return clienteRepository.save(nuevo);
                });

        mudanza.setCliente(cliente);

        // ==========================
        // COLABORADORES
        // ==========================

        List<Colaborador> colaboradores =
                colaboradorRepository.findAllById(dto.colaboradores());

        if (colaboradores.size() != dto.colaboradores().size()) {
            throw new RuntimeException("Uno o más colaboradores no existen.");
        }

        mudanza.setColaboradores(colaboradores);

        // ==========================
        // GUARDAR
        // ==========================

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

    // 🔹 Actualizar
    public MudanzaResponseDTO actualizar(Long id, MudanzaRequestDTO dto) {

        Mudanza existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mudanza no encontrada"));

        existente.setFecha(dto.fecha());
        existente.setHora(dto.hora());
        existente.setLugarRecogida(dto.lugarRecogida());
        existente.setLugarEntrega(dto.lugarEntrega());
        existente.setDescripcion(dto.descripcion());
        existente.setEstado(dto.estado());

        // ==========================
        // CLIENTE
        // ==========================

        var clienteDTO = dto.cliente();

        Cliente cliente = clienteRepository.findByTelefono(clienteDTO.telefono())
                .orElseGet(() -> {
                    Cliente nuevo = new Cliente();
                    nuevo.setNombre(clienteDTO.nombre());
                    nuevo.setTelefono(clienteDTO.telefono());
                    nuevo.setEmail(clienteDTO.email());
                    return clienteRepository.save(nuevo);
                });

        existente.setCliente(cliente);

        // ==========================
        // COLABORADORES
        // ==========================

        List<Colaborador> colaboradores =
                colaboradorRepository.findAllById(dto.colaboradores());

        if (colaboradores.size() != dto.colaboradores().size()) {
            throw new RuntimeException("Uno o más colaboradores no existen.");
        }

        existente.setColaboradores(colaboradores);

        Mudanza actualizada = repository.save(existente);

        return mapper.toResponseDTO(actualizada);
    }

    // 🔹 Eliminar
    public void eliminar(Long id) {

        Mudanza existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mudanza no encontrada"));

        repository.delete(existente);
    }
}