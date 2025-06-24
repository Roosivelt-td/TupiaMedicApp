package com.clinica.citas.dto.response;

import lombok.Data;

@Data
public class PacienteResponse {
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
}