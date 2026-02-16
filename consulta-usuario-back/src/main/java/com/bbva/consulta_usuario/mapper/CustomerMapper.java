package com.bbva.consulta_usuario.mapper;


import com.bbva.consulta_usuario.model.dto.CustomerResponseDto;
import com.bbva.consulta_usuario.model.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDto toDto(CustomerEntity entity);
}