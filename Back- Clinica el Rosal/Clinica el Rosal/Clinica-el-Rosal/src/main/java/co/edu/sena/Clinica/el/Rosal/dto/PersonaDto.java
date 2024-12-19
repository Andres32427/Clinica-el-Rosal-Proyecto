package co.edu.sena.Clinica.el.Rosal.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
public class PersonaDto {

    private Long id;

    private String nombre;

    private String apellido;

    private String genero;

    private Date fechaNacimiento;

    private String tipoIdentificacion;

    private String identificacion;

    private String idSeguro;

    private String telefono;

    private String correo;

    private String direccion;

    private String grupoSanguineo;

    private String alergias;

    private String tipoDeAlergia;

    private Long idMunicipio;

}
