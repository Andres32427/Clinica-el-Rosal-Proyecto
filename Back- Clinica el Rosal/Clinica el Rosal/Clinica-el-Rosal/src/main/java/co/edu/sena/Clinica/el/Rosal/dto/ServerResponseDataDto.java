package co.edu.sena.Clinica.el.Rosal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerResponseDataDto {

    private String message;
    private int status;
    private Object data;
}
