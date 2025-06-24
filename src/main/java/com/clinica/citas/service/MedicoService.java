package com.clinica.citas.service;

import com.clinica.citas.dto.request.MedicoRequest;
import com.clinica.citas.dto.response.MedicoResponse;
import java.util.List;

public interface MedicoService {
    MedicoResponse crearMedico(MedicoRequest request);
    MedicoResponse obtenerMedicoPorId(Long id);
    List<MedicoResponse> obtenerTodosLosMedicos();
    List<MedicoResponse> obtenerMedicosPorEspecialidad(String especialidad);
    void actualizarMedico(Long id, MedicoRequest request);
    void eliminarMedico(Long id);
}