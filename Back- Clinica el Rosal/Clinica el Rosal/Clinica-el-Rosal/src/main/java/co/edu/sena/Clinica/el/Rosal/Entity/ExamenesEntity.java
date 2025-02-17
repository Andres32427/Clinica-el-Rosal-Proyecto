package co.edu.sena.Clinica.el.Rosal.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_examenes")
public class ExamenesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se asegura que se genera automáticamente
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "id_tipo_examen")
    private Long idTipoExamen;

    @Column(name = "fecha_examen")
    private LocalDate fechaExamen;

    @Column(name = "archivo_examen")
    private String archivoExamen;

    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_auxiliar")
    private Long idAuxiliar;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
