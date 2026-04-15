package com.chavo.mudanza;

import com.chavo.mudanza.dto.MudanzaRequestDTO;
import com.chavo.mudanza.dto.MudanzaResponseDTO;
import com.chavo.mudanza.entity.Mudanza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MudanzaMapper {

    // RequestDTO → Entity
    @Mapping(target = "cliente", ignore = true)
    Mudanza toEntity(MudanzaRequestDTO dto);


    // Entity → ResponseDTO
    @Mapping(source = "cliente.nombre", target = "nombreCliente")
    @Mapping(source = "cliente.telefono", target = "telefonoCliente")
    MudanzaResponseDTO toResponseDTO(Mudanza mudanza);

}