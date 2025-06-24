package com.clinica.citas.service.mapper;

import com.clinica.citas.dto.response.PacienteResponse;
import com.clinica.citas.model.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    public PacienteResponse toResponse(Paciente paciente) {
        return new PacienteResponse(
                paciente.getId(),
                paciente.getDni(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getEmail(),
                paciente.getTelefono()
        );
    }
}