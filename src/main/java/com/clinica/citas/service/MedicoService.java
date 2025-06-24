package com.clinica.citas.service;

import com.clinica.citas.dto.request.MedicoRequest;
import com.clinica.citas.dto.response.MedicoResponse;
import java.util.List;

public interface MedicoService {
    MedicoResponse crearMedico(MedicoRequest medicoRequest);
    List<MedicoResponse> obtenerTodosLosMedicos();
    MedicoResponse obtenerMedicoPorId(Long id);
    List<MedicoResponse> obtenerMedicosPorEspecialidad(String especialidad);
    MedicoResponse actualizarMedico(Long id, MedicoRequest medicoRequest);
    void eliminarMedico(Long id);
}