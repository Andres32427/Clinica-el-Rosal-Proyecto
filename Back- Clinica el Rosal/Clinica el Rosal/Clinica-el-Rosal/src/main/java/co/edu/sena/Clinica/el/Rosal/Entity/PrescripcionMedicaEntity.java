package co.edu.sena.Clinica.el.Rosal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "prescripción_medica")
public class PrescripcionMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se asegura que se genera automáticamente
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // Relacion de la prescripcion medica con la Historia clinica
    @ManyToOne // Se Indica que Varias Prescripciones medicas pueden pertenecer a un solo Historial Clinico
    @JoinColumn(name = "id_historia") // Se Especifica la la columna de la tabla 
    private HistoriaClinicaEntity historia;

    @Column(name = "id_medicamentos")
    private Long idMedicamentos;

    @Column(name = "cantidad_total")
    private int cantidadTotal;

    @Column(name = "presentacion")
    private String presentacion;

    @Column(name = "indicaciones")
    private String indicaciones;
}
