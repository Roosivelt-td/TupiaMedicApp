package com.clinica.citas.service.impl;

import com.clinica.citas.dto.request.CitaRequest;
import com.clinica.citas.dto.response.CitaResponse;
import com.clinica.citas.model.Cita;
import com.clinica.citas.model.Medico;
import com.clinica.citas.model.Paciente;
import com.clinica.citas.repository.CitaRepository;
import com.clinica.citas.repository.MedicoRepository;
import com.clinica.citas.repository.PacienteRepository;
import com.clinica.citas.service.CitaService;
import com.clinica.citas.service.mapper.CitaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final CitaMapper citaMapper;

    @Override
    public CitaResponse crearCita(CitaRequest citaRequest) {
        if (existeCitaSolapada(citaRequest.getMedicoId(), citaRequest.getFechaHora())) {
            throw new RuntimeException("El médico ya tiene una cita programada en ese horario");
        }

        Medico medico = medicoRepository.findById(citaRequest.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        Paciente paciente = pacienteRepository.findByDni(citaRequest.getPacienteDni())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Cita cita = new Cita();
        cita.setMedico(medico);
        cita.setPaciente(paciente);
        cita.setFechaHora(citaRequest.getFechaHora());
        cita.setEstado("PROGRAMADA");

        Cita citaGuardada = citaRepository.save(cita);
        return citaMapper.toResponse(citaGuardada);
    }

    @Override
    public boolean existeCitaSolapada(Long medicoId, LocalDateTime fechaHora) {
        LocalDateTime inicio = fechaHora.minusMinutes(29);
        LocalDateTime fin = fechaHora.plusMinutes(29);
        return !citaRepository.findByMedicoIdAndFechaHoraBetween(medicoId, inicio, fin).isEmpty();
    }

    @Override
    public CitaResponse actualizarCita(Long id, CitaRequest citaRequest) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if (existeCitaSolapada(citaRequest.getMedicoId(), citaRequest.getFechaHora())) {
            throw new RuntimeException("El médico ya tiene una cita programada en ese horario");
        }

        Medico medico = medicoRepository.findById(citaRequest.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        Paciente paciente = pacienteRepository.findByDni(citaRequest.getPacienteDni())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        cita.setMedico(medico);
        cita.setPaciente(paciente);
        cita.setFechaHora(citaRequest.getFechaHora());

        Cita citaActualizada = citaRepository.save(cita);
        return citaMapper.toResponse(citaActualizada);
    }

    @Override
    public void cancelarCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado("CANCELADA");
        citaRepository.save(cita);
    }

    @Override
    public List<CitaResponse> obtenerCitasPorPaciente(String dni) {
        return citaRepository.findByPacienteDni(dni).stream()
                .map(citaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaResponse> obtenerCitasPorMedico(Long medicoId) {
        return citaRepository.findByMedicoId(medicoId).stream()
                .map(citaMapper::toResponse)
                .collect(Collectors.toList());
    }
}