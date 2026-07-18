package com.chavo.mudanza.controller;

import com.chavo.mudanza.dto.ColaboradorDTO;
import com.chavo.mudanza.entity.EstadoColaborador;
import com.chavo.mudanza.service.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
@CrossOrigin(origins = "*")
public class ColaboradorController {

    private final ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }

    // 🔹 Crear colaborador
    @PostMapping
    public ColaboradorDTO crear(@RequestBody @Valid ColaboradorDTO dto) {
        return service.crear(dto);
    }

    // 🔹 Obtener todos
    @GetMapping
    public List<ColaboradorDTO> obtenerTodos() {
        return service.obtenerTodos();
    }

    // 🔹 Obtener por ID
    @GetMapping("/{id}")
    public ColaboradorDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // 🔹 Obtener por estado
    @GetMapping("/estado/{estado}")
    public List<ColaboradorDTO> obtenerPorEstado(
            @PathVariable EstadoColaborador estado) {
        return service.obtenerPorEstado(estado);
    }

    // 🔹 Buscar por nombre
    @GetMapping("/buscar")
    public List<ColaboradorDTO> buscarPorNombre(
            @RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }

    // 🔹 Actualizar
    @PutMapping("/{id}")
    public ColaboradorDTO actualizar(
            @PathVariable Long id,
            @RequestBody @Valid ColaboradorDTO dto) {

        return service.actualizar(id, dto);
    }

    // 🔹 Desactivar
    @PutMapping("/{id}/desactivar")
    public void desactivar(@PathVariable Long id) {
        service.desactivar(id);
    }

    // 🔹 Activar
    @PutMapping("/{id}/activar")
    public void activar(@PathVariable Long id) {
        service.activar(id);
    }
}