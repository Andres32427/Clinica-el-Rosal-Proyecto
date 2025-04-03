package co.edu.sena.Clinica.el.Rosal.dto;


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
public class LoginResponseDTO {

    private Long id;

    private Long idPaciente;

    private Long idMedico;

    private Long idAuxiliar;

    private Long idFarmaceutico;

    private Long idRol;

    private boolean isActive;
    
    private String message;
}
