package com.clinica.citas.service;

import com.clinica.citas.dto.request.PacienteRequest;
import com.clinica.citas.dto.response.PacienteResponse;
import java.util.List;

public interface PacienteService {
    PacienteResponse crearPaciente(PacienteRequest pacienteRequest);
    List<PacienteResponse> obtenerTodosLosPacientes();
    PacienteResponse obtenerPacientePorId(Long id);
    PacienteResponse obtenerPacientePorDni(String dni);
    PacienteResponse actualizarPaciente(Long id, PacienteRequest pacienteRequest);
    void eliminarPaciente(Long id);
}