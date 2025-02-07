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
@Table (name = "agendamiento")
public class AgendamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Se asegura que se genera automáticamente
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "id_especialidad")
    private Long idEspecialidad;

    @Column(name = "sede")
    private String sede;

    @Column(name = "estado")
    private String estado;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "id_usuario_creador")
    private Long idUsuarioCreador;

    @Column(name = "tipo_creador")
    private String tipoCreador;

}
