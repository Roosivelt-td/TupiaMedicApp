package com.clinica.citas.dto.response;

import lombok.Data;

@Data
public class MedicoResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;
}