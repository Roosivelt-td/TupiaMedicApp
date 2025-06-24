package com.clinica.citas.dto.response;

public record PacienteResponse(
        Long id,
        String dni,
        String nombre,
        String apellido,
        String email,
        String telefono
) {}