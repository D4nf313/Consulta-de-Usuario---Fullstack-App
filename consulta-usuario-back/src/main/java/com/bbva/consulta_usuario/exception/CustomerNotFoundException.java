package com.bbva.consulta_usuario.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(
            Integer documentTypeId,
            String documentNumber
    ) {
        super(String.format("Cliente no encontrado con tipo de documento: %d y número: %s",
                documentTypeId, documentNumber));
    }
}