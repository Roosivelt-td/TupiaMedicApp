package com.clinica.citas.service.mapper;

import com.clinica.citas.dto.response.MedicoResponse;
import com.clinica.citas.model.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {
    public MedicoResponse toResponse(Medico medico) {
        return new MedicoResponse(
                medico.getId(),
                medico.getNombre(),
                medico.getApellido(),
                medico.getEspecialidad(),
                medico.getEmail(),
                medico.getTelefono()
        );
    }
}