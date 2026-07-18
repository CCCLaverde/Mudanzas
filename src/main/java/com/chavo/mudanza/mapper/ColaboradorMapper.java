package com.chavo.mudanza.mapper;

import com.chavo.mudanza.dto.ColaboradorDTO;
import com.chavo.mudanza.entity.Colaborador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper {

    Colaborador toEntity(ColaboradorDTO dto);

    ColaboradorDTO toDTO(Colaborador colaborador);

}
