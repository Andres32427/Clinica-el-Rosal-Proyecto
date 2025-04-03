package co.edu.sena.Clinica.el.Rosal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.UsuarioService;
import co.edu.sena.Clinica.el.Rosal.dto.GuardarPasswordRequestDto;
import co.edu.sena.Clinica.el.Rosal.dto.LoginRequestDTO;
import co.edu.sena.Clinica.el.Rosal.dto.LoginResponseDTO;
import co.edu.sena.Clinica.el.Rosal.dto.ResetPasswordRequestDto;
import co.edu.sena.Clinica.el.Rosal.dto.ResetPasswordResponseDto;
import co.edu.sena.Clinica.el.Rosal.dto.ServerResponseDataDto;
import co.edu.sena.Clinica.el.Rosal.dto.ValidacionCodigoRequestDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService service;

    // Se Realiza el controller para la ejecucion del Login 
    @PostMapping()
    public ServerResponseDataDto login(@RequestBody LoginRequestDTO requestDTO) {

        // Se Logra pausar la ejecución actual por 4 segundos para simular algún procesamiento o espera.
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // Si se ocurre una interrupción durante la pausa, se lanza una excepción Runtime para detener el flujo.
            throw new RuntimeException(e);
        }

        //Se hace el Llamado al servicio login con los datos recibidos en el cuerpo de la solicitud (requestDTO)
        LoginResponseDTO responseDTO = this.service.login(requestDTO);

        // Se construye y devuelve una respuesta al servidor si fue ingresado correctamente con su status 200 o no 
        return ServerResponseDataDto.builder()
        .message("Ingreso al Sistema Exitoso")
        .data(responseDTO)
        .status(200)
        .build();
    }

    //Se Ejecuta este controller con el fin de poder obtener el restablecimiento de contraseña 
    @PostMapping("/resetPassword")
    public ResponseEntity<ResetPasswordResponseDto> resetPassword(@RequestBody ResetPasswordRequestDto requestDto) {

        try {
            
            // Se le Hace el llamado al servicio para poder procesar la solicitud del restablecimiento
            ResetPasswordResponseDto responseDto = service.responseDto(requestDto);

            // En este Caso se Verifica el Resultado de la busqueda y devuelve la respuesta adecuada
            if (!responseDto.isSuccess()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto); // En este caso aparecera si el usuario no existe dentro del sistema
            }

            return ResponseEntity.ok(responseDto); // Pero si cumple con todos los filtros anteriores el codigo se enviaria correctamente

        }catch (Exception e) {
            
            // Aqui vamos a manejar los errores que se vean inesperado
            ResetPasswordResponseDto errorResponse = ResetPasswordResponseDto.builder()
            .success(false)
            .message("Hubo un error en el Procesamiento de la solicitud:" + e.getMessage())
            .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse); // Este Seria el encargado de manejar los errores inesperados 
        }
    }

    // Se Crea este controller con el fin de obtener la validacion del codigo de restablecimiento
    @PostMapping("/validacion-codigo")
    public ResetPasswordResponseDto validacionCodigo(@RequestBody ValidacionCodigoRequestDto requestDto) {
        return service.validacionCodigo(requestDto);
    }

    // Se Crea este controller para poder guardar la nueva contraseña del Login
    @PostMapping("/Guardar-nuevaPassword")
    public ResponseEntity<ResetPasswordResponseDto> guardarNuevaContraseña(@RequestBody GuardarPasswordRequestDto requestDto) {

        // Se llama al Service para poder manejar la logica de la actualizacion de la contraseña en la base de datos 
        ResetPasswordResponseDto responseDto = service.guardarNuevaContraseña(requestDto); 

        // Si se pudo verificar la actualizacion de la contraseña se enviara un http 200
        if (responseDto.isSuccess()) {
            return ResponseEntity.ok(responseDto);

         // Pero si no se verifico es probable que se deba a un error por lo cual lo que hara sera enviar un http 400 badRequest
        }else {
            return ResponseEntity.badRequest().body(responseDto);
        }
    }
}
