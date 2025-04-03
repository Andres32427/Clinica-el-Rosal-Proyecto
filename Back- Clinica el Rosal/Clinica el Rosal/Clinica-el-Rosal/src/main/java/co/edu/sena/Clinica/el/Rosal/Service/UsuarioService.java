package co.edu.sena.Clinica.el.Rosal.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.UsuarioEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.UsuarioRepository;
import co.edu.sena.Clinica.el.Rosal.dto.GuardarPasswordRequestDto;
import co.edu.sena.Clinica.el.Rosal.dto.LoginRequestDTO;
import co.edu.sena.Clinica.el.Rosal.dto.LoginResponseDTO;
import co.edu.sena.Clinica.el.Rosal.dto.ResetPasswordRequestDto;
import co.edu.sena.Clinica.el.Rosal.dto.ResetPasswordResponseDto;
import co.edu.sena.Clinica.el.Rosal.dto.ValidacionCodigoRequestDto;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmailService service;

    // Este Servicio cumple la funcion principal para la Autenticacion al Usuario
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {

        LoginResponseDTO responseDTO;

        // Se Busca al Usuario por Login (email/username)
        Optional<UsuarioEntity> optionalUsuario = repository.findByLogin(requestDTO.getUsername());
        if (optionalUsuario.isEmpty()) {
            return LoginResponseDTO.builder()
            .isActive(false)
            .message("El Usuario No fue Encontrado")
            .build();
        }

        UsuarioEntity entity = optionalUsuario.get();

        // Ahora se Valida la contraseña 
        if (!requestDTO.getPassword().equals(entity.getPassword())) {

            // Se Incrementa los Intentos fallidos y verifica el bloqueo de la cuenta
            entity.setIntentosFallidos(entity.getIntentosFallidos() + 1);
            if (entity.getIntentosFallidos() >= 3) {

                repository.save(entity);

                return LoginResponseDTO.builder()
                .isActive(false)
                .message("Se ha Bloqueado la cuenta por demasiados intentos fallidos")
                .build();
            }

            repository.save(entity);
            return LoginResponseDTO.builder()
            .isActive(false)
            .message("Contraseña Incorrecta")
            .build();
        }

        // Login ingresado con exito : De esta manera se reiniciaria los Intentos Fallidos anteriores
        entity.setIntentosFallidos(0);
        repository.save(entity);

        // Construccion de la respuesta del sistema
        responseDTO = LoginResponseDTO.builder()
        .id(entity.getId())
        .idPaciente(entity.getIdPaciente())
        .idMedico(entity.getIdMedico())
        .idAuxiliar(entity.getIdAuxiliar())
        .idFarmaceutico(entity.getIdFarmaceutico())
        .idRol(entity.getIdRol())
        .isActive(true)
        .message("Login Ingresado de manera exitosa")
        .build();

        return responseDTO;
    }


    // Se Crea este servicio para hacer la solicitud del Restablecimiento de la contraseña 
    public ResetPasswordResponseDto responseDto(ResetPasswordRequestDto requestDto) { 
        
        // Se Empieza a Hacer la Busqueda al usuario por medio de su email
        Optional<UsuarioEntity> optionalUsuario = repository.findByLogin(requestDto.getEmail());
        if (optionalUsuario.isEmpty()) { 

            return ResetPasswordResponseDto.builder()
            .success(false)
            .message("El Usuario No Pudo Ser Encontrado")
            .build();
        }

        UsuarioEntity entity = optionalUsuario.get();

        // Se Genera el codigo unico para el restablecimiento de la contraseña
        String codigo = UUID.randomUUID().toString().substring(0, 6);

        // Se Configuraria el codigo y su tiempo de expiracion
        entity.setCodigoRestablecimiento(codigo);
        entity.setExpiracionCodigo(LocalDateTime.now().plusMinutes(15)); // Tiempo estipulado para la expiracion del codigo
        repository.save(entity);

        // Se Hace el Envio del correo con el codigo de restablecimiento al destinatario
        service.sendEmail(
            requestDto.getEmail(),
            "Codigo de Restablecimiento",
            "TU CODIGO DE RESTABLECIMIENTO DE LA CONTRASEÑA ES:" + codigo
        );

        // Ahora se construye  y se retorna la respuesta que dara el sistema 
        return ResetPasswordResponseDto.builder()
        .success(true)
        .message("El Codigo fue enviado al Correo Electronico")
        .build();
    }

    
    // Se Genera este Servicio con el fin de obtener la validacion del codigo de restablecimiento
    public ResetPasswordResponseDto validacionCodigo(ValidacionCodigoRequestDto requestDto) {

        // Empezamos Buscando por el Correo del paciente
        Optional<UsuarioEntity> optionalUsuario = repository.findByLogin(requestDto.getEmail());

        // Si no se encuentra el correo se enviara un mensaje diciendo que el usuario con ese email no existe
        if (!optionalUsuario.isPresent()) {

            return ResetPasswordResponseDto.builder()
            .success(false)
            .message("No existe un usuario con ese email.")
            .codigoRestablecimiento(null)
            .build();
        }

        UsuarioEntity entity = optionalUsuario.get();

        // Se Verifica que si obtuvo un codigo de restablecimiento
        if (entity.getCodigoRestablecimiento() == null) {
            
            return ResetPasswordResponseDto.builder()
            .success(false)
            .message("No se ha generado un código de restablecimiento para este usuario.")
            .codigoRestablecimiento(null)
            .build();
        }

        // Se Verifica si el codigo coincide con el que se envio
        if (!entity.getCodigoRestablecimiento().equals(requestDto.getCodigo())) {
            
            return ResetPasswordResponseDto.builder()
            .success(false)
            .message("El código ingresado es incorrecto.")
            .codigoRestablecimiento(null)
            .build();
        }

        // Se hace la Validacion de la expiracion del codigo
        if (entity.getExpiracionCodigo() != null && entity.getExpiracionCodigo().isBefore(LocalDateTime.now())) {
            
            // Código Fue ya expirado
            return ResetPasswordResponseDto.builder()
            .success(false)
            .message("El código ya se ha expirado, tiene que solicita uno nuevo.")
            .codigoRestablecimiento(null)
            .build();
        }

        repository.save(entity);

        return ResetPasswordResponseDto.builder()
        .success(true)
        .message("El código de restablecimiento es válido.")
        .codigoRestablecimiento(null) 
        .build();
    }


    // Se Genera este nuevo Servicio con el fin de obtener la nueva contraseña luego de haber pasado correctamente los anteriores servicios
    public ResetPasswordResponseDto guardarNuevaContraseña(GuardarPasswordRequestDto requestDto) {

        // Empezamos Buscando por el Correo del paciente
        Optional<UsuarioEntity> optionalUsuario = repository.findByLogin(requestDto.getEmail());

        // Aqui empezamos viendo que si el usuario no fue encontrado dentro de la busqueda en la BD enviara un mensaje al postman diciendo que el usuario no fue encontrado
        if (optionalUsuario.isEmpty()) {
            
            return ResetPasswordResponseDto.builder()
            .success(false)
            .message("El Usuario no puedo ser encontrado dentro del sistema")
            .build();
        }

        // Se Obtiene el objeto del UsuarioEntity que es almacenado dentro del optional
        UsuarioEntity entity = optionalUsuario.get();

        // En este caso se verifia si el codigo de restablecimiento es valido
        if (!requestDto.getCodigo().equals(entity.getCodigoRestablecimiento())) {
            
            return ResetPasswordResponseDto.builder()
            .success(false)
            .message("Codigo de restablecimiento incorrecto")
            .build();
        }

        // Aqui es cuando ya se verifica si el codigo de restablecimiento ya fue Expirado 
        if (entity.getExpiracionCodigo().isBefore(LocalDateTime.now())) {
            
            return ResetPasswordResponseDto.builder()
            .success(false)
            .message("Codigo de restablecimiento ya se ha Expirado")
            .build();
        }

        // Se Guarda la Nueva contraseña en la base de datos 
        entity.setPassword(requestDto.getNuevaPassword());

        // Se hace el guardado en la base de datos
        repository.save(entity);
        
        return ResetPasswordResponseDto.builder()
        .success(true)
        .message("La Contraseña fue Actualizada Correctamente")
        .build();

    }
}
