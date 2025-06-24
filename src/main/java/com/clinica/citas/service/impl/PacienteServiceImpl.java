package com.clinica.citas.service.impl;

import com.clinica.citas.dto.request.PacienteRequest;
import com.clinica.citas.dto.response.PacienteResponse;
import com.clinica.citas.model.Paciente;
import com.clinica.citas.repository.PacienteRepository;
import com.clinica.citas.service.PacienteService;
import com.clinica.citas.service.mapper.PacienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    @Override
    public PacienteResponse crearPaciente(PacienteRequest pacienteRequest) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByDni(pacienteRequest.dni());
        if (pacienteExistente.isPresent()) {
            throw new RuntimeException("Ya existe un paciente con este DNI");
        }

        Paciente paciente = new Paciente();
        paciente.setDni(pacienteRequest.dni());
        paciente.setNombre(pacienteRequest.nombre());
        paciente.setApellido(pacienteRequest.apellido());
        paciente.setEmail(pacienteRequest.email());
        paciente.setTelefono(pacienteRequest.telefono());

        Paciente pacienteGuardado = pacienteRepository.save(paciente);
        return pacienteMapper.toResponse(pacienteGuardado);
    }

    @Override
    public List<PacienteResponse> obtenerTodosLosPacientes() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteResponse obtenerPacientePorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return pacienteMapper.toResponse(paciente);
    }

    @Override
    public PacienteResponse obtenerPacientePorDni(String dni) {
        Paciente paciente = pacienteRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return pacienteMapper.toResponse(paciente);
    }

    @Override
    public PacienteResponse actualizarPaciente(Long id, PacienteRequest pacienteRequest) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        paciente.setDni(pacienteRequest.dni());
        paciente.setNombre(pacienteRequest.nombre());
        paciente.setApellido(pacienteRequest.apellido());
        paciente.setEmail(pacienteRequest.email());
        paciente.setTelefono(pacienteRequest.telefono());

        Paciente pacienteActualizado = pacienteRepository.save(paciente);
        return pacienteMapper.toResponse(pacienteActualizado);
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente no encontrado");
        }
        pacienteRepository.deleteById(id);
    }
}