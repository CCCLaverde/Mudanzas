package com.chavo.mudanza.service;

import com.chavo.mudanza.dto.ColaboradorEstadisticaDTO;
import com.chavo.mudanza.repository.MudanzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadisticaService {

    private final MudanzaRepository mudanzaRepository;

    public EstadisticaService(MudanzaRepository mudanzaRepository) {
        this.mudanzaRepository = mudanzaRepository;
    }

    public List<ColaboradorEstadisticaDTO> obtenerEstadisticasColaboradores(
            Integer mes,
            Integer anio
    ) {

        return mudanzaRepository.obtenerEstadisticasColaboradores(mes, anio);

    }

}