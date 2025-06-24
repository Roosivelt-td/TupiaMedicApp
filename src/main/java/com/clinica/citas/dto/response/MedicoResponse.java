package com.clinica.citas.dto.response;

public record MedicoResponse(
        Long id,
        String nombre,
        String apellido,
        String especialidad,
        String email,
        String telefono
) {}