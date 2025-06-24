package com.clinica.citas.service.mapper;

import com.clinica.citas.dto.response.MedicoResponse;
import com.clinica.citas.model.Medico;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MedicoMapper {

    public MedicoResponse toMedicoResponse(Medico medico) {
        MedicoResponse response = new MedicoResponse();
        response.setId(medico.getId());
        response.setNombre(medico.getNombre());
        response.setApellido(medico.getApellido());
        response.setEspecialidad(medico.getEspecialidad());
        response.setTelefono(medico.getTelefono());
        response.setEmail(medico.getEmail());
        return response;
    }

    public List<MedicoResponse> toListMedicoResponse(List<Medico> medicos) {
        List<MedicoResponse> responses = new ArrayList<>();
        if (medicos != null && !medicos.isEmpty()) {
            for (Medico medico : medicos) {
                responses.add(toMedicoResponse(medico));
            }
        }
        return responses;
    }
}