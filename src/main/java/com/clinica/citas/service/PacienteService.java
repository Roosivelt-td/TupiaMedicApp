package com.clinica.citas.service;

import com.clinica.citas.dto.request.PacienteRequest;
import com.clinica.citas.dto.response.PacienteResponse;
import java.util.List;

public interface PacienteService {
    PacienteResponse crearPaciente(PacienteRequest request);
    PacienteResponse obtenerPacientePorId(Long id);
    PacienteResponse obtenerPacientePorDni(String dni);
    List<PacienteResponse> obtenerTodosLosPacientes();
    List<PacienteResponse> buscarPacientesPorNombre(String nombre);
    void actualizarPaciente(Long id, PacienteRequest request);
    void eliminarPaciente(Long id);
}