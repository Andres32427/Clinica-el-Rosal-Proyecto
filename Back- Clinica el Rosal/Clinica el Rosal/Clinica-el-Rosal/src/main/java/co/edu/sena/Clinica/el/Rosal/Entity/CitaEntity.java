package co.edu.sena.Clinica.el.Rosal.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cita")
public class CitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se asegura que se genera automáticamente
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "Hora")
    private LocalTime hora;

    @Column(name = "Estado")
    private String estado;

    @Column(name = "id_especialidad")
    private Long idEspecialidad;

}
