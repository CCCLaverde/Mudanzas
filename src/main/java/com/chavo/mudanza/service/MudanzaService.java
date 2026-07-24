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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MudanzaService {

    private final MudanzaRepository repository;
    private final MudanzaMapper mapper;
    private final ClienteRepository clienteRepository;
    private final ColaboradorRepository colaboradorRepository;

    public MudanzaService(
            MudanzaRepository repository,
            MudanzaMapper mapper,
            ClienteRepository clienteRepository,
            ColaboradorRepository colaboradorRepository) {

        this.repository = repository;
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
        this.colaboradorRepository = colaboradorRepository;
    }

    // =====================================================
    // CREAR
    // =====================================================

    public MudanzaResponseDTO crear(MudanzaRequestDTO dto) {

        Mudanza mudanza = mapper.toEntity(dto);

        if (mudanza.getEstado() == null) {
            mudanza.setEstado(EstadoMudanza.PENDIENTE);
        }

        mudanza.setCliente(obtenerOCrearCliente(dto));

        mudanza.setColaboradores(obtenerColaboradores(dto.colaboradores()));

        return mapper.toResponseDTO(repository.save(mudanza));
    }

    // =====================================================
    // CONSULTAS
    // =====================================================

    public List<MudanzaResponseDTO> obtenerTodas() {

        return repository.findAll(
                        Sort.by(
                                Sort.Order.desc("fecha"),
                                Sort.Order.desc("hora")
                        )
                )
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public MudanzaResponseDTO obtenerPorId(Long id) {

        return mapper.toResponseDTO(buscarMudanza(id));
    }

    public List<MudanzaResponseDTO> obtenerPorFecha(LocalDate fecha) {

        return repository.findByFecha(fecha)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public List<MudanzaResponseDTO> obtenerPorEstado(EstadoMudanza estado) {

        return repository.findByEstado(estado)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // =====================================================
// FILTROS
// =====================================================

    // 🔹 Obtener por colaborador
    public List<MudanzaResponseDTO> obtenerPorColaborador(Long colaboradorId) {

        return repository.findByColaboradores_Id(colaboradorId)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // 🔹 Obtener por fecha y colaborador
    public List<MudanzaResponseDTO> obtenerPorFechaYColaborador(
            LocalDate fecha,
            Long colaboradorId) {

        return repository.findByFechaAndColaboradores_Id(fecha, colaboradorId)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // 🔹 Obtener por rango de fechas y colaborador
    public List<MudanzaResponseDTO> obtenerPorRangoYColaborador(
            LocalDate inicio,
            LocalDate fin,
            Long colaboradorId) {

        return repository.findByFechaBetweenAndColaboradores_Id(
                        inicio,
                        fin,
                        colaboradorId)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    // =====================================================
    // ACTUALIZAR
    // =====================================================

    public MudanzaResponseDTO actualizar(Long id, MudanzaRequestDTO dto) {

        Mudanza existente = buscarMudanza(id);

        existente.setFecha(dto.fecha());
        existente.setHora(dto.hora());
        existente.setLugarRecogida(dto.lugarRecogida());
        existente.setLugarEntrega(dto.lugarEntrega());
        existente.setDescripcion(dto.descripcion());
        existente.setEstado(dto.estado());

        existente.setCliente(obtenerOCrearCliente(dto));

        existente.setColaboradores(obtenerColaboradores(dto.colaboradores()));

        return mapper.toResponseDTO(repository.save(existente));
    }

    // =====================================================
    // ELIMINAR
    // =====================================================

    public void eliminar(Long id) {

        repository.delete(buscarMudanza(id));
    }

    // =====================================================
    // MÉTODOS PRIVADOS
    // =====================================================

    private Mudanza buscarMudanza(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mudanza no encontrada"));
    }

    private Cliente obtenerOCrearCliente(MudanzaRequestDTO dto) {

        var clienteDTO = dto.cliente();

        return clienteRepository.findByTelefono(clienteDTO.telefono())
                .orElseGet(() -> {

                    Cliente nuevo = new Cliente();

                    nuevo.setNombre(clienteDTO.nombre());
                    nuevo.setTelefono(clienteDTO.telefono());
                    nuevo.setEmail(clienteDTO.email());

                    return clienteRepository.save(nuevo);
                });
    }

    public List<MudanzaResponseDTO> obtenerPorRango(
            LocalDate inicio,
            LocalDate fin) {

        return repository.findByFechaBetween(inicio, fin)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    private List<Colaborador> obtenerColaboradores(List<Long> ids) {

        List<Colaborador> colaboradores =
                colaboradorRepository.findAllById(ids);

        if (colaboradores.size() != ids.size()) {
            throw new RuntimeException("Uno o más colaboradores no existen.");
        }

        return colaboradores;
    }
}