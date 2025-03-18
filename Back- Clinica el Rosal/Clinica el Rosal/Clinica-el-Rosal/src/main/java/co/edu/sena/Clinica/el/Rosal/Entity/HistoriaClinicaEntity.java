package co.edu.sena.Clinica.el.Rosal.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "historia")
public class HistoriaClinicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se asegura que se genera automáticamente
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // Relacion de la Historia Clinica con Paciente
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false) // Nombre de la columna en la base de datos
    private PacienteEntity paciente;

    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "fecha_consulta")
    private LocalDateTime fechaConsulta;

    @Column(name = "motivo_consulta")
    private String motivoConsulta;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "tratamiento")
    private String tratamiento;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "antecedentes")
    private String antecedentes;

    @Column(name = "signos_vitales")
    private String signosVitales;

    @Column(name = "examenes_solicitado")
    private String examenesSolicitado;

    // Relacion de la Historia Clinica con la prescripcion medica
    @OneToMany(mappedBy = "historia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescripcionMedicaEntity> prescripcionMedica = new ArrayList<>();
}
