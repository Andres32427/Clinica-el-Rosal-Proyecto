package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Entity.CitaEntity;
import co.edu.sena.Clinica.el.Rosal.Service.CitaService;
import co.edu.sena.Clinica.el.Rosal.dto.CitaDto;
import co.edu.sena.Clinica.el.Rosal.dto.ServerResponseDataDto;

@RestController
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    private CitaService service;

    // Se encuentra este controlador para obtener todas las Citas registradas dentro del sistema
    @GetMapping()
    public ResponseEntity<List<CitaEntity>> obtenerTodasLasCitas(){
        return ResponseEntity.ok(service.obtenerTodasLasCitas());
    }

    // Se Gestiona el Controlador para obtener Cita por medio del ID del paciente
    @GetMapping("/{id}")
    public ResponseEntity<CitaEntity> obtenerCitaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerCitaPorId(id));
    }


    // Este Controlador se encarga de Gestionar una Nueva cita 
    @PostMapping()
    public ServerResponseDataDto create(@RequestBody CitaDto request) {

        service.save(request);

        return ServerResponseDataDto.builder()
        .message("Se ha Agendado Correctamente la Cita Medica!")
        .status(HttpStatus.OK.value())
        .data(null)
        .build();
    }

    // Este Controlador ayuda a que la Actualizacion de la Cita si todo esta en orden sea Actualizada Correctamente
    @PutMapping("/{id}")
    public ServerResponseDataDto actualizarCita(
        @PathVariable("id") Long id, 
        @RequestBody CitaDto request) {

        request.setId(id);
        request = this.service.actualizarCita(request);

        return ServerResponseDataDto
        .builder()
        .message(request != null ? "CITA ACTUALIZADA CORRECTAMENTE" : "NO SE PUDO ACTUALIZAR LA CITA INTENTELO NUEVAMENTE")
        .status(request != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value() )
        .data(request)
        .build();
    }

    // Este Controlador Sirve para Reprogramar la Cita ya asignada anteriormente de Manera Correcta
    @PutMapping("/{id}/reprogramar")
    public ServerResponseDataDto reprogramarCita(
        @PathVariable("id") Long id, 
        @RequestBody CitaDto request) {

        request.setId(id);
        request = this.service.reprogramarCita(request.getId(), request);

        return ServerResponseDataDto
        .builder()
        .message(request != null ? "CITA REPROGRAMADA CORRECTAMENTE" : "NO SE PUDO REPROGRAMAR LA CITA INTENTELO NUEVAMENTE")
        .status(request != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value() )
        .data(request)
        .build();
    }

    //Este Controlador sirve para Cancelar la cita Asignada
    @PutMapping("/{id}/cancelar")
    public ServerResponseDataDto cancelarCita(
        @PathVariable("id") Long id, 
        @RequestBody CitaDto request) {

        request.setId(id);
        request = this.service.cancelarCita(request.getId(), request);

        return ServerResponseDataDto
        .builder()
        .message(request != null ? "CITA CANCELADA CORRECTAMENTE" : "NO SE PUDO CANCELAR LA CITA INTENTELO NUEVAMENTE")
        .status(request != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value() )
        .data(request)
        .build();
    }

    // Se Elimina De manera inmediata la Cita solicitada
    @DeleteMapping("/{id}")
    public ServerResponseDataDto eliminarCita(@PathVariable("id") Long id){

        this.service.eliminarCita(id);

        return ServerResponseDataDto
        .builder()
        .message("Cita Eliminada Correctamente")
        .status( HttpStatus.OK.value())
        .build();
    }

    
}
