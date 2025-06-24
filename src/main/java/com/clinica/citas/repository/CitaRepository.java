package com.clinica.citas.repository;

import com.clinica.citas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMedicoIdAndFechaHoraBetween(Long medicoId, LocalDateTime inicio, LocalDateTime fin);
    List<Cita> findByPacienteId(Long pacienteId);
    boolean existsByMedicoIdAndFechaHora(Long medicoId, LocalDateTime fechaHora);

    // Métodos adicionales necesarios
    List<Cita> findByMedicoId(Long medicoId);

    @Query("SELECT c FROM Cita c WHERE c.medico.id = :medicoId AND c.fechaHora BETWEEN :inicio AND :fin ORDER BY c.fechaHora")
    List<Cita> findCitasDelDia(@Param("medicoId") Long medicoId,
                               @Param("inicio") LocalDateTime inicio,
                               @Param("fin") LocalDateTime fin);
}