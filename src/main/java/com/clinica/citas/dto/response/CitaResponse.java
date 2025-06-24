package com.clinica.citas.dto.response;

import java.time.LocalDateTime;

public record CitaResponse(
        Long id,
        String pacienteNombre,
        String pacienteDni,
        String medicoNombre,
        String medicoEspecialidad,
        LocalDateTime fechaHora,
        String estado
) {}