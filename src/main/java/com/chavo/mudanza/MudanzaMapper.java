package com.chavo.mudanza;

import com.chavo.mudanza.dto.MudanzaRequestDTO;
import com.chavo.mudanza.dto.MudanzaResponseDTO;
import com.chavo.mudanza.entity.Mudanza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MudanzaMapper {

    // RequestDTO → Entity
    Mudanza toEntity(MudanzaRequestDTO dto);

    // Entity → ResponseDTO
    MudanzaResponseDTO toResponseDTO(Mudanza mudanza);

}