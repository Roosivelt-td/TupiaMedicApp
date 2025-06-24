package com.clinica.citas.controller;

import com.clinica.citas.dto.request.PacienteRequest;
import com.clinica.citas.dto.response.PacienteResponse;
import com.clinica.citas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteResponse> crearPaciente(@RequestBody PacienteRequest request) {
        return ResponseEntity.ok(pacienteService.crearPaciente(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> obtenerPacientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obtenerPacientePorId(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<PacienteResponse> obtenerPacientePorDni(@PathVariable String dni) {
        return ResponseEntity.ok(pacienteService.obtenerPacientePorDni(dni));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> obtenerTodosLosPacientes() {
        return ResponseEntity.ok(pacienteService.obtenerTodosLosPacientes());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PacienteResponse>> buscarPacientesPorNombre(
            @RequestParam String nombre) {
        return ResponseEntity.ok(pacienteService.buscarPacientesPorNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarPaciente(
            @PathVariable Long id, @RequestBody PacienteRequest request) {
        pacienteService.actualizarPaciente(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}