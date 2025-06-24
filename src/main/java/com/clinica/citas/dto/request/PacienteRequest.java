package com.clinica.citas.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteRequest {
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
}