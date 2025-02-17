package co.edu.sena.Clinica.el.Rosal.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Entity.ExamenesEntity;
import co.edu.sena.Clinica.el.Rosal.Service.ExamenesService;
import co.edu.sena.Clinica.el.Rosal.dto.ExamenesDto;
import co.edu.sena.Clinica.el.Rosal.dto.ServerResponseDataDto;

@RestController
@RequestMapping("/detalle_examenes")
public class ExamenesController {

    @Autowired
    private ExamenesService service;

    // Se Creo el controlador del cual es el encargado de subir el examen del paciente al sistema desde el postman
    @PostMapping()
    @PreAuthorize("hasRole('AUXILIAR')") // Se le Asigno una restriccion para que solo el Auxiliar pueda subir el examen
    public ServerResponseDataDto create(@RequestBody ExamenesDto request) {

        service.save(request); // Se le llama al servicio para guardar la informacion del dto a la base de datos 

        return ServerResponseDataDto.builder()
        .message("Examen Subido al Sistema del Paciente Correctamente!")
        .status(HttpStatus.OK.value())
        .data(null)
        .build();
    }

    // Se Creo este Controlador el cual permite obtener los examenes de los pacientes por medio de su ID Unico
    @GetMapping("/paciente/{idPaciente}")
    @PreAuthorize("hasRole('PACIENTE')")
    public ResponseEntity<List<ExamenesEntity>> obtenerExamen(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(service.obtenerExamen(idPaciente));
    }

    // Se obtiene Todos los Examenes que se ha registrado dentro del sistema
    @GetMapping()
    public ResponseEntity<List<ExamenesEntity>> obtenerTodosLosExamenes() {
        return ResponseEntity.ok(service.obtenerTodosLosExamenes());
    }

    // Este controlador obtiene los Examenes subidos del auxiliar al paciente correspondiente
    @GetMapping("/auxiliar/{idAuxiliar}")
    @PreAuthorize("hasRole('AUXILIAR')")
    public ResponseEntity<List<ExamenesEntity>> obtenerExamenSubido(@PathVariable Long idAuxiliar) {
        return ResponseEntity.ok(service.obtenerExamenSubido(idAuxiliar));
    }


    // Este Controlador Actualiza Los datos de un Examen medico
    @PutMapping("/{id}")
    public ServerResponseDataDto update(     // Se Trata de que el metodo a proporcionar como el update para ejecutarse cuando se haga el PUT
        @PathVariable ("id") Long id,   // Se Realiza la variable del parametro del ID del examen que se va a solicitar en ese momento
        @RequestBody ExamenesDto request) {  // En esta situacion lo que indica es materializar el cuerpo del PUT que se solicite a JSON

        request.setId(id); // Se Establece el ID que se solicito en el request que es el cuerpo de la solicitud que se envia al Postman
        request = this.service.update(request); // Y en este caso el servicio se encarga de verificar el ID del examen que se solicito y actualizarlo en la base de datos 

        return ServerResponseDataDto
        .builder()
        .message(request != null ? "Examen Actualizado" : "El Examen presento un inconvenienten para ser Actualizado")
        .status(request != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value() )
        .data(request)
        .build();
    }

    // Este Contolador ayuda a eliminar el ID del examen que se solicite 
    @DeleteMapping("/{id}")
    public ServerResponseDataDto deleteById(@PathVariable("id") Long id ) { // Ayuda a ejecutar la solicitud del Delete en la cual tambien se recibe su ID correspondiente a eliminar

        this.service.delete(id); // Se le llama al servicio a hacer su funcion de eliminar el ID del examen de la base de datos

        return ServerResponseDataDto
        .builder()
        .message("Examen Eliminado")
        .status( HttpStatus.OK.value())
        .build();
    }
}
