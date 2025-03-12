package co.edu.sena.Clinica.el.Rosal.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class HistoriaClinicaDTO {

    private Long id;

    private Long idPaciente;

    private Long idMedico;

    private LocalDateTime fechaConsulta;

    private String motivoConsulta;

    private String diagnostico;

    private String tratamiento;

    private String alergias;

    private String antecedentes;

    private String signosVitales;

    private String examenesSolicitado;

    private List<PrescripcionMedicaDTO> prescripcionMedica;
}
