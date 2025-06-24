package com.clinica.citas.service.mapper;

import com.clinica.citas.dto.response.PacienteResponse;
import com.clinica.citas.model.Paciente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PacienteMapper {

    public PacienteResponse toPacienteResponse(Paciente paciente) {
        PacienteResponse response = new PacienteResponse();
        response.setId(paciente.getId());
        response.setDni(paciente.getDni());
        response.setNombre(paciente.getNombre());
        response.setApellido(paciente.getApellido());
        response.setTelefono(paciente.getTelefono());
        response.setEmail(paciente.getEmail());
        response.setDireccion(paciente.getDireccion());
        return response;
    }

    public List<PacienteResponse> toListPacienteResponse(List<Paciente> pacientes) {
        List<PacienteResponse> responses = new ArrayList<>();
        if (pacientes != null && !pacientes.isEmpty()) {
            for (Paciente paciente : pacientes) {
                responses.add(toPacienteResponse(paciente));
            }
        }
        return responses;
    }
}