package com.clinica.citas.dto.request;

public record MedicoRequest(
        String nombre,
        String apellido,
        String especialidad,
        String email,
        String telefono
) {}