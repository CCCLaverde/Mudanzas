package com.chavo.mudanza.controller;

import com.chavo.mudanza.dto.ClienteDTO;
import com.chavo.mudanza.service.ClienteServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServices service;

    public ClienteController(ClienteServices service) {
        this.service = service;
    }

    // Crear cliente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO crear(@Valid @RequestBody ClienteDTO dto) {
        return service.crear(dto);
    }

    // Obtener todos los clientes
    @GetMapping
    public List<ClienteDTO> obtenerTodos() {
        return service.obtenerTodos();
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ClienteDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // Obtener cliente por teléfono
    @GetMapping("/telefono/{telefono}")
    public ClienteDTO obtenerPorTelefono(@PathVariable String telefono) {
        return service.obtenerPorTelefono(telefono);
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public ClienteDTO actualizar(@PathVariable Long id,
                                 @Valid @RequestBody ClienteDTO dto) {
        return service.actualizar(id, dto);
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}