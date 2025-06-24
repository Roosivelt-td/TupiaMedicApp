package com.clinica.citas.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CitaResponse {
    private Long id;
    private Long pacienteId;
    private String pacienteNombre;
    private Long medicoId;
    private String medicoNombre;
    private String especialidad;
    private LocalDateTime fechaHora;
    private String estado;
    private String motivo;
}