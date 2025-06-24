package com.clinica.citas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "medicos")
@Data
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;

    @OneToMany(mappedBy = "medico")
    private Set<Cita> citas;
}