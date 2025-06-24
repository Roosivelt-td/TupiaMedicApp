package com.clinica.citas.service.impl;

import com.clinica.citas.dto.request.MedicoRequest;
import com.clinica.citas.dto.response.MedicoResponse;
import com.clinica.citas.model.Medico;
import com.clinica.citas.repository.MedicoRepository;
import com.clinica.citas.service.MedicoService;
import com.clinica.citas.service.mapper.MedicoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    @Override
    public MedicoResponse crearMedico(MedicoRequest medicoRequest) {
        Medico medico = new Medico();
        medico.setNombre(medicoRequest.nombre());
        medico.setApellido(medicoRequest.apellido());
        medico.setEspecialidad(medicoRequest.especialidad());
        medico.setEmail(medicoRequest.email());
        medico.setTelefono(medicoRequest.telefono());

        Medico medicoGuardado = medicoRepository.save(medico);
        return medicoMapper.toResponse(medicoGuardado);
    }

    @Override
    public List<MedicoResponse> obtenerTodosLosMedicos() {
        return medicoRepository.findAll().stream()
                .map(medicoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MedicoResponse obtenerMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        return medicoMapper.toResponse(medico);
    }

    @Override
    public List<MedicoResponse> obtenerMedicosPorEspecialidad(String especialidad) {
        return medicoRepository.findByEspecialidad(especialidad).stream()
                .map(medicoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MedicoResponse actualizarMedico(Long id, MedicoRequest medicoRequest) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        medico.setNombre(medicoRequest.nombre());
        medico.setApellido(medicoRequest.apellido());
        medico.setEspecialidad(medicoRequest.especialidad());
        medico.setEmail(medicoRequest.email());
        medico.setTelefono(medicoRequest.telefono());

        Medico medicoActualizado = medicoRepository.save(medico);
        return medicoMapper.toResponse(medicoActualizado);
    }

    @Override
    public void eliminarMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico no encontrado");
        }
        medicoRepository.deleteById(id);
    }
}