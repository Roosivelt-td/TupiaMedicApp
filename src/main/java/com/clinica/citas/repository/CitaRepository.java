package com.clinica.citas.repository;

import com.clinica.citas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMedicoIdAndFechaHoraBetween(Long medicoId, LocalDateTime start, LocalDateTime end);
    List<Cita> findByPacienteDni(String dni);
}