package com.bbva.consulta_usuario.controller;


import com.bbva.consulta_usuario.model.dto.CustomerResponseDto;
import com.bbva.consulta_usuario.service.CustomerQueryService;
import com.bbva.consulta_usuario.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerQueryService customerQueryService;

    private static final String URL =
            "/api/v1/customers/search/by-document";

    @Test
    void shouldReturnCustomerWhenParamsAreValid() throws Exception {

        // 1️⃣ Arrange (preparar datos)
        CustomerResponseDto response = new CustomerResponseDto();
        response.setCustomerId("CUST-001");
        response.setFirstName("Juan");
        response.setLastName("Pérez");
        response.setIdentityDocument("DNI");
        response.setIdentityDocumentNumber("12345678");
        response.setIdentityDocumentTypeId(1);

        // 2️⃣ Mock del service
        Mockito.when(customerQueryService.findCustomerByDocument(1, "12345678"))
                .thenReturn(response);

        // 3️⃣ Act + Assert
        mockMvc.perform(get("/api/v1/customers/search/by-document")
                        .param("identityDocumentTypeId", "1")
                        .param("identityDocumentNumber", "12345678")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value("CUST-001"))
                .andExpect(jsonPath("$.firstName").value("Juan"))
                .andExpect(jsonPath("$.lastName").value("Pérez"));
    }

    @Test
    void shouldReturn400WhenDocumentTypeIsMissing() throws Exception {

        mockMvc.perform(get(URL)
                        .param("identityDocumentNumber", "12345678"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("BAD_REQUEST"));
    }

    @Test
    void shouldReturn400WhenDocumentNumberIsBlank() throws Exception {

        mockMvc.perform(get(URL)
                        .param("identityDocumentTypeId", "1")
                        .param("identityDocumentNumber", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("BAD_REQUEST"));
    }

    @Test
    void shouldReturn404WhenCustomerNotFound() throws Exception {

        Mockito.when(customerQueryService.findCustomerByDocument(1, "99999999"))
                .thenThrow(new CustomerNotFoundException(
                        1,
                        "99999999"
                ));

        mockMvc.perform(get(URL)
                        .param("identityDocumentTypeId", "1")
                        .param("identityDocumentNumber", "99999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("CUSTOMER_NOT_FOUND"))
                .andExpect(jsonPath("$.message")
                        .value("Cliente no encontrado para tipoDocumento=1 y numeroDocumento=99999999"));

    }

    @Test
    void shouldReturn500WhenUnexpectedErrorOccurs() throws Exception {

        Mockito.when(customerQueryService.findCustomerByDocument(1, "123"))
                .thenThrow(new RuntimeException("Boom"));

        mockMvc.perform(get(URL)
                        .param("identityDocumentTypeId", "1")
                        .param("identityDocumentNumber", "123"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value("INTERNAL_ERROR"));
    }
}
