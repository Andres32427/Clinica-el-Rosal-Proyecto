package co.edu.sena.Clinica.el.Rosal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

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
public class FacturacionDto {

    private Long id;

    private Long idPaciente;

    private Long idServicio;

    private BigDecimal monto;

    private LocalDate fecha;

}
