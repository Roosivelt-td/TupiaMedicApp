package com.clinica.citas.service.mapper;

import com.clinica.citas.dto.response.CitaResponse;
import com.clinica.citas.model.Cita;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CitaMapper {

    public CitaResponse toCitaResponse(Cita cita) {
        CitaResponse response = new CitaResponse();
        response.setId(cita.getId());
        response.setPacienteId(cita.getPaciente().getId());
        response.setPacienteNombre(cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellido());
        response.setMedicoId(cita.getMedico().getId());
        response.setMedicoNombre(cita.getMedico().getNombre() + " " + cita.getMedico().getApellido());
        response.setEspecialidad(cita.getMedico().getEspecialidad());
        response.setFechaHora(cita.getFechaHora());
        response.setEstado(cita.getEstado());
        response.setMotivo(cita.getMotivo());
        return response;
    }

    public List<CitaResponse> toListCitaResponse(List<Cita> citas) {
        List<CitaResponse> responses = new ArrayList<>();
        if (citas != null && !citas.isEmpty()) {
            for (Cita cita : citas) {
                responses.add(toCitaResponse(cita));
            }
        }
        return responses;
    }
}