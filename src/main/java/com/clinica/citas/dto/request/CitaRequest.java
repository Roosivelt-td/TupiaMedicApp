package com.clinica.citas.dto.request;

import java.time.LocalDateTime;

public record CitaRequest(
        Long medicoId,
        String pacienteDni,
        LocalDateTime fechaHora
) {}