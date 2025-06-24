package com.clinica.citas.service.impl;

import com.clinica.citas.dto.request.MedicoRequest;
import com.clinica.citas.dto.response.MedicoResponse;
import com.clinica.citas.model.Medico;
import com.clinica.citas.repository.MedicoRepository;
import com.clinica.citas.service.MedicoService;
import com.clinica.citas.service.mapper.MedicoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoMapper medicoMapper;

    @Override
    public MedicoResponse crearMedico(MedicoRequest request) {
        Medico medico = new Medico();
        medico.setNombre(request.getNombre());
        medico.setApellido(request.getApellido());
        medico.setEspecialidad(request.getEspecialidad());
        medico.setTelefono(request.getTelefono());
        medico.setEmail(request.getEmail());

        medico = medicoRepository.save(medico);
        return medicoMapper.toMedicoResponse(medico);
    }

    @Override
    public MedicoResponse obtenerMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        return medicoMapper.toMedicoResponse(medico);
    }

    @Override
    public List<MedicoResponse> obtenerTodosLosMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        return medicoMapper.toListMedicoResponse(medicos);
    }

    @Override
    public List<MedicoResponse> obtenerMedicosPorEspecialidad(String especialidad) {
        List<Medico> medicos = medicoRepository.findByEspecialidad(especialidad);
        return medicoMapper.toListMedicoResponse(medicos);
    }

    @Override
    public void actualizarMedico(Long id, MedicoRequest request) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        medico.setNombre(request.getNombre());
        medico.setApellido(request.getApellido());
        medico.setEspecialidad(request.getEspecialidad());
        medico.setTelefono(request.getTelefono());
        medico.setEmail(request.getEmail());

        medicoRepository.save(medico);
    }

    @Override
    public void eliminarMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico no encontrado");
        }
        medicoRepository.deleteById(id);
    }
}