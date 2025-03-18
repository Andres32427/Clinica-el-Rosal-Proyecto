package co.edu.sena.Clinica.el.Rosal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import co.edu.sena.Clinica.el.Rosal.Entity.HistoriaClinicaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class PrescripcionMedicaDTO {

    private Long id;

    private HistoriaClinicaEntity historia;

    private Long idMedicamentos;

    private int cantidadTotal;

    private String presentacion;

    private String indicaciones;
}
