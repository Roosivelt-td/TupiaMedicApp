package com.clinica.citas.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoRequest {
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;
}