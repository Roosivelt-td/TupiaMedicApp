package com.clinica.citas.service;

import com.clinica.citas.dto.request.CitaRequest;
import com.clinica.citas.dto.response.CitaResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {
    CitaResponse programarCita(CitaRequest request);
    List<CitaResponse> obtenerCitasPorPaciente(Long pacienteId);
    List<CitaResponse> obtenerCitasPorMedico(Long medicoId);
    CitaResponse obtenerCitaPorId(Long id);
    void cancelarCita(Long id);
    List<CitaResponse> obtenerCitasDisponibles(Long medicoId, LocalDateTime fecha);
}