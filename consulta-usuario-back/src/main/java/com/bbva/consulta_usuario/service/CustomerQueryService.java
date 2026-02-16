package com.bbva.consulta_usuario.service;

import com.bbva.consulta_usuario.model.dto.CustomerResponseDto;

public interface CustomerQueryService {

    CustomerResponseDto findCustomerByDocument(
            Integer identityDocumentTypeId,
            String identityDocumentNumber
    );
}