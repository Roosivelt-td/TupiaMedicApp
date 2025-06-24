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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CitaMapper citaMapper;

    @Override
    public CitaResponse programarCita(CitaRequest request) {
        // Validar que el paciente existe
        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Validar que el médico existe
        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        // Validar disponibilidad del médico
        if (citaRepository.existsByMedicoIdAndFechaHora(request.getMedicoId(), request.getFechaHora())) {
            throw new RuntimeException("El médico ya tiene una cita programada en ese horario");
        }

        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setFechaHora(request.getFechaHora());
        cita.setMotivo(request.getMotivo());
        cita.setEstado("Programada");

        cita = citaRepository.save(cita);

        return citaMapper.toCitaResponse(cita);
    }

    @Override
    public List<CitaResponse> obtenerCitasPorPaciente(Long pacienteId) {
        List<Cita> citas = citaRepository.findByPacienteId(pacienteId);
        return citaMapper.toListCitaResponse(citas);
    }

    @Override
    public List<CitaResponse> obtenerCitasPorMedico(Long medicoId) {
        List<Cita> citas = citaRepository.findByMedicoId(medicoId);
        return citaMapper.toListCitaResponse(citas);
    }

    @Override
    public CitaResponse obtenerCitaPorId(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        return citaMapper.toCitaResponse(cita);
    }

    @Override
    public void cancelarCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado("Cancelada");
        citaRepository.save(cita);
    }

    @Override
    public List<CitaResponse> obtenerCitasDisponibles(Long medicoId, LocalDateTime fecha) {
        // Validar que el médico existe
        if (!medicoRepository.existsById(medicoId)) {
            throw new RuntimeException("Médico no encontrado");
        }

        // Convertir la fecha a inicio y fin del día
        LocalDateTime inicioDia = fecha.with(LocalTime.MIN);
        LocalDateTime finDia = fecha.with(LocalTime.MAX);

        // Obtener citas programadas para ese médico en ese día
        List<Cita> citasProgramadas = citaRepository.findByMedicoIdAndFechaHoraBetween(
                medicoId, inicioDia, finDia);

        // Aquí podrías implementar lógica para determinar los horarios disponibles
        // basado en las citas ya programadas y el horario laboral del médico
        // Este es un ejemplo simplificado que devuelve las citas programadas
        // (en un caso real, deberías calcular los espacios disponibles)

        return citaMapper.toListCitaResponse(citasProgramadas);
    }
}