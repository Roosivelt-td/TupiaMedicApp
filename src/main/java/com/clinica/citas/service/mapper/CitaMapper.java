package com.clinica.citas.service.mapper;

import com.clinica.citas.dto.response.CitaResponse;
import com.clinica.citas.model.Cita;
import org.springframework.stereotype.Component;

@Component
public class CitaMapper {
    public CitaResponse toResponse(Cita cita) {
        return new CitaResponse(
                cita.getId(),
                cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellido(),
                cita.getPaciente().getDni(),
                cita.getMedico().getNombre() + " " + cita.getMedico().getApellido(),
                cita.getMedico().getEspecialidad(),
                cita.getFechaHora(),
                cita.getEstado()
        );
    }
}