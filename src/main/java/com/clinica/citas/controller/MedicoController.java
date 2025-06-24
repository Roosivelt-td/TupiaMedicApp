package com.clinica.citas.controller;

import com.clinica.citas.dto.request.MedicoRequest;
import com.clinica.citas.dto.response.MedicoResponse;
import com.clinica.citas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoResponse> crearMedico(@RequestBody MedicoRequest request) {
        return ResponseEntity.ok(medicoService.crearMedico(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> obtenerMedicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.obtenerMedicoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> obtenerTodosLosMedicos() {
        return ResponseEntity.ok(medicoService.obtenerTodosLosMedicos());
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<MedicoResponse>> obtenerMedicosPorEspecialidad(
            @PathVariable String especialidad) {
        return ResponseEntity.ok(medicoService.obtenerMedicosPorEspecialidad(especialidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarMedico(
            @PathVariable Long id, @RequestBody MedicoRequest request) {
        medicoService.actualizarMedico(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id) {
        medicoService.eliminarMedico(id);
        return ResponseEntity.noContent().build();
    }
}