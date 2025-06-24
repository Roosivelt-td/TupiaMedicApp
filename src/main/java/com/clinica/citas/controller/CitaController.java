package com.clinica.citas.controller;

import com.clinica.citas.dto.request.CitaRequest;
import com.clinica.citas.dto.response.CitaResponse;
import com.clinica.citas.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @PostMapping
    public ResponseEntity<CitaResponse> crearCita(@RequestBody CitaRequest request) {
        return ResponseEntity.ok(citaService.crearCita(request));
    }

    @GetMapping("/paciente/{dni}")
    public ResponseEntity<List<CitaResponse>> obtenerCitasPorPaciente(@PathVariable String dni) {
        return ResponseEntity.ok(citaService.obtenerCitasPorPaciente(dni));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<CitaResponse>> obtenerCitasPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(citaService.obtenerCitasPorMedico(medicoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaResponse> actualizarCita(
            @PathVariable Long id, @RequestBody CitaRequest request) {
        return ResponseEntity.ok(citaService.actualizarCita(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id) {
        citaService.cancelarCita(id);
        return ResponseEntity.noContent().build();
    }
}