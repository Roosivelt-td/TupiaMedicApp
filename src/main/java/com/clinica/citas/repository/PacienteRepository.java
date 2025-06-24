package com.clinica.citas.repository;

import com.clinica.citas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByDni(String dni);
    List<Paciente> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByDni(String dni);
}