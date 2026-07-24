package com.chavo.mudanza.controller;

import com.chavo.mudanza.dto.MudanzaRequestDTO;
import com.chavo.mudanza.dto.MudanzaResponseDTO;
import com.chavo.mudanza.entity.EstadoMudanza;
import com.chavo.mudanza.service.MudanzaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mudanzas")
@CrossOrigin(origins = "http://localhost:5173")
public class MudanzaController {

    private final MudanzaService service;

    public MudanzaController(MudanzaService service) {
        this.service = service;
    }

    // ==========================
    // CRUD
    // ==========================

    @PostMapping
    public MudanzaResponseDTO crear(@RequestBody @Valid MudanzaRequestDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<MudanzaResponseDTO> obtenerTodas() {
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public MudanzaResponseDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public MudanzaResponseDTO actualizar(
            @PathVariable Long id,
            @RequestBody @Valid MudanzaRequestDTO dto) {

        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ==========================
    // FILTROS
    // ==========================

    @GetMapping("/fecha")
    public List<MudanzaResponseDTO> obtenerPorFecha(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha) {

        return service.obtenerPorFecha(fecha);
    }

    @GetMapping("/rango-colaborador")
    public List<MudanzaResponseDTO> obtenerPorRangoYColaborador(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate inicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fin,

            @RequestParam Long colaboradorId) {

        return service.obtenerPorRangoYColaborador(
                inicio,
                fin,
                colaboradorId
        );
    }

    @GetMapping("/rango")
    public List<MudanzaResponseDTO> obtenerPorRango(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate inicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fin) {

        return service.obtenerPorRango(inicio, fin);
    }

    @GetMapping("/estado")
    public List<MudanzaResponseDTO> obtenerPorEstado(
            @RequestParam EstadoMudanza estado) {

        return service.obtenerPorEstado(estado);
    }

    @GetMapping("/colaborador/{id}")
    public List<MudanzaResponseDTO> obtenerPorColaborador(
            @PathVariable Long id) {

        return service.obtenerPorColaborador(id);
    }

    @GetMapping("/fecha-colaborador")
    public List<MudanzaResponseDTO> obtenerPorFechaYColaborador(

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha,

            @RequestParam Long colaboradorId) {

        return service.obtenerPorFechaYColaborador(
                fecha,
                colaboradorId
        );
    }
}