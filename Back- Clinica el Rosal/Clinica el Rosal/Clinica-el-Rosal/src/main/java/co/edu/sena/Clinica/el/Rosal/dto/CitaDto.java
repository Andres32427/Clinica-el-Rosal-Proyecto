package co.edu.sena.Clinica.el.Rosal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class CitaDto {

    private Long id;

    private Long idPaciente;

    private Long idMedico;

    private LocalDate fecha;

    private LocalTime hora;

    private String estado;

    private Long idEspecialidad;

}
