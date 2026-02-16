package com.bbva.consulta_usuario.controller;

import com.bbva.consulta_usuario.model.dto.CustomerResponseDto;
import com.bbva.consulta_usuario.service.CustomerQueryService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerQueryService customerQueryService;

    @GetMapping("/search/by-document")
    public ResponseEntity<CustomerResponseDto> searchCustomer(
            @RequestParam
            @NotNull(message = "El tipo de documento es obligatorio")
            Integer identityDocumentTypeId,

            @RequestParam
            @NotBlank(message = "El número de documento es obligatorio")
            String identityDocumentNumber
    ) {

        CustomerResponseDto customer =
                customerQueryService.findCustomerByDocument(
                        identityDocumentTypeId,
                        identityDocumentNumber
                );

        return ResponseEntity.ok(customer);
    }
}