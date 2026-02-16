package com.bbva.consulta_usuario.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private String customerId;
    private String firstName;
    private String lastName;
    private String nationalityId;
    private String genderId;
    private String identityDocument;
    private String identityDocumentNumber;
    private Integer identityDocumentTypeId;
}