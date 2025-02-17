package co.edu.sena.Clinica.el.Rosal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class ExamenesDto {

    private Long id;

    private Long idTipoExamen;

    private LocalDate fechaExamen;

    private String archivoExamen;

    private Long idPaciente;

    private Long idAuxiliar;

    private LocalDateTime createdAt;
}
