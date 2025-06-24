package com.clinica.citas.service.impl;

import com.clinica.citas.dto.request.PacienteRequest;
import com.clinica.citas.dto.response.PacienteResponse;
import com.clinica.citas.model.Paciente;
import com.clinica.citas.repository.PacienteRepository;
import com.clinica.citas.service.PacienteService;
import com.clinica.citas.service.mapper.PacienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteMapper pacienteMapper;

    @Override
    public PacienteResponse crearPaciente(PacienteRequest request) {
        // Validar que el DNI no esté registrado
        if (pacienteRepository.existsByDni(request.getDni())) {
            throw new RuntimeException("Ya existe un paciente con este DNI");
        }

        Paciente paciente = new Paciente();
        paciente.setDni(request.getDni());
        paciente.setNombre(request.getNombre());
        paciente.setApellido(request.getApellido());
        paciente.setTelefono(request.getTelefono());
        paciente.setEmail(request.getEmail());
        paciente.setDireccion(request.getDireccion());

        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.toPacienteResponse(paciente);
    }

    @Override
    public PacienteResponse obtenerPacientePorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return pacienteMapper.toPacienteResponse(paciente);
    }

    @Override
    public PacienteResponse obtenerPacientePorDni(String dni) {
        Paciente paciente = pacienteRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        return pacienteMapper.toPacienteResponse(paciente);
    }

    @Override
    public List<PacienteResponse> obtenerTodosLosPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacienteMapper.toListPacienteResponse(pacientes);
    }

    @Override
    public List<PacienteResponse> buscarPacientesPorNombre(String nombre) {
        List<Paciente> pacientes = pacienteRepository.findByNombreContainingIgnoreCase(nombre);
        return pacienteMapper.toListPacienteResponse(pacientes);
    }

    @Override
    public void actualizarPaciente(Long id, PacienteRequest request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        paciente.setNombre(request.getNombre());
        paciente.setApellido(request.getApellido());
        paciente.setTelefono(request.getTelefono());
        paciente.setEmail(request.getEmail());
        paciente.setDireccion(request.getDireccion());

        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente no encontrado");
        }
        pacienteRepository.deleteById(id);
    }
}