package com.bbva.consulta_usuario.service.impl;

import com.bbva.consulta_usuario.exception.CustomerNotFoundException;
import com.bbva.consulta_usuario.mapper.CustomerMapper;
import com.bbva.consulta_usuario.model.dto.CustomerResponseDto;
import com.bbva.consulta_usuario.repository.CustomerRepository;
import com.bbva.consulta_usuario.service.CustomerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;



    @Override
    public CustomerResponseDto findCustomerByDocument(
            Integer identityDocumentTypeId,
            String identityDocumentNumber
    ) {

        // 1️⃣ Validaciones de entrada (reglas de negocio básicas)
        if (identityDocumentTypeId == null) {
            throw new IllegalArgumentException("El tipo de documento es obligatorio");
        }

        if (!StringUtils.hasText(identityDocumentNumber)) {
            throw new IllegalArgumentException("El número de documento es obligatorio");
        }

        // 2️⃣ Consulta al repositorio
        return customerRepository
                .findByIdentityDocumentTypeIdAndIdentityDocumentNumber(
                        identityDocumentTypeId,
                        identityDocumentNumber
                )
                // 3️⃣ Manejo del caso no encontrado (regla de negocio)
                .map(customerMapper::toDto)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                identityDocumentTypeId,
                                identityDocumentNumber
                        )
                );
    }
}