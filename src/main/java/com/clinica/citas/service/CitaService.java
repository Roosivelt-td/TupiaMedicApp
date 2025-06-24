package com.clinica.citas.service;

import com.clinica.citas.dto.request.CitaRequest;
import com.clinica.citas.dto.response.CitaResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {
    CitaResponse crearCita(CitaRequest citaRequest);
    CitaResponse actualizarCita(Long id, CitaRequest citaRequest);
    void cancelarCita(Long id);
    List<CitaResponse> obtenerCitasPorPaciente(String dni);
    List<CitaResponse> obtenerCitasPorMedico(Long medicoId);
    boolean existeCitaSolapada(Long medicoId, LocalDateTime fechaHora);
}