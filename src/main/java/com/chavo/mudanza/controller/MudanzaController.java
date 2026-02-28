package com.chavo.mudanza.controller;

import com.chavo.mudanza.Service.MudanzaService;
import com.chavo.mudanza.dto.MudanzaRequestDTO;
import com.chavo.mudanza.dto.MudanzaResponseDTO;
import com.chavo.mudanza.entity.EstadoMudanza;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mudanzas")
@CrossOrigin // Permite conexión con tu frontend
public class MudanzaController {

    private final MudanzaService service;

    public MudanzaController(MudanzaService service) {
        this.service = service;
    }

    // 🔹 Crear mudanza
    @PostMapping
    public MudanzaResponseDTO crear(@RequestBody MudanzaRequestDTO dto) {
        return service.crear(dto);
    }

    // 🔹 Obtener todas
    @GetMapping
    public List<MudanzaResponseDTO> obtenerTodas() {
        return service.obtenerTodas();
    }

    // 🔹 Obtener por ID
    @GetMapping("/{id}")
    public MudanzaResponseDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // 🔹 Obtener por fecha (para tu filtro del frontend)
    @GetMapping("/fecha")
    public List<MudanzaResponseDTO> obtenerPorFecha(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha) {
        return service.obtenerPorFecha(fecha);
    }

    // 🔹 Obtener por estado
    @GetMapping("/estado")
    public List<MudanzaResponseDTO> obtenerPorEstado(
            @RequestParam EstadoMudanza estado) {
        return service.obtenerPorEstado(estado);
    }

    // 🔹 Actualizar mudanza
    @PutMapping("/{id}")
    public MudanzaResponseDTO actualizar(
            @PathVariable Long id,
            @RequestBody MudanzaRequestDTO dto) {
        return service.actualizar(id, dto);
    }

    // 🔹 Eliminar mudanza
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

