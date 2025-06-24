package com.clinica.citas.dto.request;

public record PacienteRequest(
        String dni,
        String nombre,
        String apellido,
        String email,
        String telefono
) {}