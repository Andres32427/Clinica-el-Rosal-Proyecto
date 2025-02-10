package co.edu.sena.Clinica.el.Rosal.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Entity.AgendamientoEntity;
import co.edu.sena.Clinica.el.Rosal.Service.AgendamientoService;
import co.edu.sena.Clinica.el.Rosal.dto.AgendamientoDto;
import co.edu.sena.Clinica.el.Rosal.dto.ServerResponseDataDto;

@RestController
@RequestMapping("/agendamiento")
public class AgendamientoController {

    @Autowired
    private AgendamientoService service;

    // Con este Controlador se Crea el Agendamiento donde solo lo puede hacer el Auxiliar
    @PostMapping()
    @PreAuthorize("hasRole('AUXILIAR')")
    public ServerResponseDataDto create(@RequestBody AgendamientoDto request) {

        service.save(request);

        return ServerResponseDataDto.builder()
        .message("Agendamiento Creado Correctamente!")
        .status(HttpStatus.OK.value())
        .data(null)
        .build();
    }

    // Obtiene los Agendamientos del medico donde solo lo puede observar el Medico
    @GetMapping("/medico/{id}")
    @PreAuthorize("hasRole('MEDICO')")
    public ResponseEntity<List<AgendamientoEntity>> verAgendamientoMedico(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerAgendamientoMedico(id));
    }

    // Este Controlador cuenta con la funcion de obtener todos los agendamientos de cada uno de los medicos por el Auxiliar
    @GetMapping("/auxiliar")
    @PreAuthorize("hasRole('AUXILIAR')")
    public ResponseEntity<List<AgendamientoEntity>> verAgendamiento(
        @RequestParam Long idMedico,@RequestParam Long idEspecialidad, 
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        return ResponseEntity.ok(service.obtenerAgendamientoMedicoEspecialidadAndFecha(idMedico, idEspecialidad, fecha));
    }

    //Este Controlador cuenta para la Modificacion del agendamiento por parte del Auxiliar
   @PutMapping("/{id}")
   @PreAuthorize("hasRole('AUXILIAR')")
    public ServerResponseDataDto modificarAgendamiento(
        @PathVariable("id") Long id, 
        @RequestBody AgendamientoDto request) {

        request.setId(id);
        request = this.service.modificarAgendamiento(request);

        return ServerResponseDataDto
        .builder()
        .message(request != null ? "Agendamiento Modificado Correctamente" : "Agendamiento Modificado Incorrectamente")
        .status(request != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value() )
        .data(request)
        .build();
    }

    // Este Controlador Su Permite Eliminar el Agendamiento medico
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AUXILIAR')")
    public ServerResponseDataDto elimininarAgendamiento(@PathVariable("id") Long id){

        this.service.eliminarAgendamiento(id);

        return ServerResponseDataDto
        .builder()
        .message("Agendamiento Eliminado Correctamente")
        .status( HttpStatus.OK.value())
        .build();
    }
}
