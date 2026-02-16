package com.bbva.consulta_usuario.exception.dto;


import java.time.LocalDateTime;

public record ErrorResponse(
        String code,
        String message,
        LocalDateTime timestamp
) {}
