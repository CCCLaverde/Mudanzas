package com.chavo.mudanza.service;

import com.chavo.mudanza.dto.ClienteDTO;
import com.chavo.mudanza.entity.Cliente;
import com.chavo.mudanza.mapper.ClienteMapper;
import com.chavo.mudanza.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServices {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteServices(ClienteRepository repository,
                           ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // Crear cliente
    public ClienteDTO crear(ClienteDTO dto) {

        // Evitar duplicados por teléfono
        repository.findByTelefono(dto.telefono())
                .ifPresent(cliente -> {
                    throw new RuntimeException("Ya existe un cliente con ese teléfono");
                });

        Cliente cliente = mapper.toEntity(dto);

        Cliente guardado = repository.save(cliente);

        return mapper.toDTO(guardado);
    }

    // Obtener todos
    public List<ClienteDTO> obtenerTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // Obtener por ID
    public ClienteDTO obtenerPorId(Long id) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return mapper.toDTO(cliente);
    }

    // Actualizar
    public ClienteDTO actualizar(Long id, ClienteDTO dto) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(dto.nombre());
        cliente.setTelefono(dto.telefono());
        cliente.setEmail(dto.email());

        Cliente actualizado = repository.save(cliente);

        return mapper.toDTO(actualizado);
    }

    // Eliminar
    public void eliminar(Long id) {

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        repository.delete(cliente);
    }

    // Obtener cliente por teléfono
    public ClienteDTO obtenerPorTelefono(String telefono) {

        Cliente cliente = repository.findByTelefono(telefono)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return mapper.toDTO(cliente);
    }
}