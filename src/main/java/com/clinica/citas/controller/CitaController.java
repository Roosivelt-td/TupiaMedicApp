package com.clinica.citas.controller;

import com.clinica.citas.dto.request.CitaRequest;
import com.clinica.citas.dto.response.CitaResponse;
import com.clinica.citas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<CitaResponse> programarCita(@RequestBody CitaRequest request) {
        return ResponseEntity.ok(citaService.programarCita(request));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<CitaResponse>> obtenerCitasPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(citaService.obtenerCitasPorPaciente(pacienteId));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<CitaResponse>> obtenerCitasPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(citaService.obtenerCitasPorMedico(medicoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id) {
        citaService.cancelarCita(id);
        return ResponseEntity.noContent().build();
    }
}