package com.chavo.mudanza.mapper;

import com.chavo.mudanza.dto.ClienteDTO;
import com.chavo.mudanza.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ClienteMapper {
    // DTO → Entity
    Cliente toEntity(ClienteDTO dto);

    // Entity → DTO
    ClienteDTO toDTO(Cliente cliente);
}
