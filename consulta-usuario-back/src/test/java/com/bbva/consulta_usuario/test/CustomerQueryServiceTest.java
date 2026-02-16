package com.bbva.consulta_usuario.test;


import com.bbva.consulta_usuario.exception.CustomerNotFoundException;
import com.bbva.consulta_usuario.mapper.CustomerMapper;
import com.bbva.consulta_usuario.model.dto.CustomerResponseDto;
import com.bbva.consulta_usuario.model.entity.CustomerEntity;
import com.bbva.consulta_usuario.repository.CustomerRepository;
import com.bbva.consulta_usuario.service.impl.CustomerQueryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerQueryServiceImplTest {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerQueryServiceImpl customerQueryService;

    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerMapper = Mockito.mock(CustomerMapper.class);
        customerQueryService = new CustomerQueryServiceImpl(customerRepository, customerMapper);
    }

    @Test
    void shouldReturnCustomerWhenParamsAreValid() {
        // 1️⃣ Crear entidad de prueba
        CustomerEntity entity = new CustomerEntity();
        entity.setId(1L);
        entity.setCustomerId("CUST-001");
        entity.setIdentityDocument("DNI");
        entity.setIdentityDocumentNumber("12345678");
        entity.setFirstName("Juan");
        entity.setLastName("Pérez");
        entity.setIdentityDocumentTypeId(1);

        // 2️⃣ Crear DTO esperado
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setCustomerId("CUST-001");
        dto.setIdentityDocument("DNI");
        dto.setIdentityDocumentNumber("12345678");
        dto.setFirstName("Juan");
        dto.setLastName("Pérez");
        dto.setIdentityDocumentTypeId(1);

        // 3️⃣ Mockear repositorio y mapper
        Mockito.when(customerRepository.findByIdentityDocumentTypeIdAndIdentityDocumentNumber(1, "12345678"))
                .thenReturn(Optional.of(entity));
        Mockito.when(customerMapper.toDto(entity)).thenReturn(dto);

        // 4️⃣ Ejecutar service
        CustomerResponseDto response = customerQueryService.findCustomerByDocument(1, "12345678");

        // 5️⃣ Verificar resultados
        assertThat(response).isNotNull();
        assertThat(response.getCustomerId()).isEqualTo("CUST-001");
        assertThat(response.getFirstName()).isEqualTo("Juan");
        assertThat(response.getLastName()).isEqualTo("Pérez");
    }

    @Test
    void shouldThrowCustomerNotFoundExceptionWhenCustomerDoesNotExist() {
        Mockito.when(customerRepository.findByIdentityDocumentTypeIdAndIdentityDocumentNumber(1, "99999999"))
                .thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> customerQueryService.findCustomerByDocument(1, "99999999"));
    }
}