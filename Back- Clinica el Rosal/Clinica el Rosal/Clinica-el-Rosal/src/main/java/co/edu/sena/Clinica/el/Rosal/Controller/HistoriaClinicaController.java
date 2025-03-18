package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.edu.sena.Clinica.el.Rosal.Service.HistoriaClinicaService;
import co.edu.sena.Clinica.el.Rosal.dto.HistoriaClinicaDTO;
import co.edu.sena.Clinica.el.Rosal.dto.ServerResponseDataDto;

@RestController
@RequestMapping("/historia")
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaService service;

    // Controlador para Gestionar la Creacion de un Nuevo Historial Clinico Junto a su Prescripcion Medica
    @PostMapping
    @PreAuthorize("hasRole('MEDICO')")
    public ServerResponseDataDto create(@RequestBody HistoriaClinicaDTO request) {

        service.save(request);

        return ServerResponseDataDto.builder()
        .message("Historia Clinica Creada Correctamente!")
        .status(HttpStatus.OK.value())
        .data(null)
        .build();
    }


    //Controlador para la Gestion de Obtener las Historias Clinicas de un paciente en especifico
    @GetMapping("/paciente/{idPaciente}")
    @PreAuthorize("hasRole('MEDICO')")
    public ServerResponseDataDto HistoriasClinicasByIdPaciente(@PathVariable Long idPaciente) {
        
        List<HistoriaClinicaDTO> historial = service.HistoriasClinicasByIdPaciente(idPaciente);

        // Se Verfica si la Lista de los Historiales Clinicos se encuentre Vacio
        if (historial.isEmpty()) {
            return ServerResponseDataDto.builder()
            .message("No Se ha Podido encontrar Historias Clinicas Relacionadas con el ID del Paciente" + idPaciente)
            .status(HttpStatus.NOT_FOUND.value())
            .data(null)
            .build();
        }

        return ServerResponseDataDto.builder()
        .message("Historias Clinicas Obtenidas Correctamente!")
        .status(HttpStatus.OK.value())
        .data(historial)
        .build();
    }


    //Controlador para Obtener toda la lista de las Historias Clinicas y prescripciones medicas de todos los pacientes
    @GetMapping()
    public ServerResponseDataDto obtenerTodasLasHistoriasClinicas() {
        
        List<HistoriaClinicaDTO> historial =  service.obtenerTodasLasHistoriasClinicas();

        return ServerResponseDataDto.builder()
        .message("Historiales Clinicos encontrados Correctamente!")
        .status(HttpStatus.OK.value())
        .data(historial)
        .build();
    }


    //Controlador para poder Actualizar Cualquier Historia clinica incluyendo tambien sus Prescripciones Medicas
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MEDICO')")
    public ServerResponseDataDto actualizarHistoriaClinica(@PathVariable Long id, @RequestBody HistoriaClinicaDTO request) {

        //Se le llama al servicio para actualizar la Historia Clínica, pasando el ID y los datos del cuerpo de la solicitud HTTP
        HistoriaClinicaDTO historial = service.actualizarHistoriaClinica(id, request);

        //Se Construye un objeto de respuesta estándar con el mensaje de éxito, el estado HTTP y los datos actualizados
        return ServerResponseDataDto.builder()
        .message("Historial Clinico Actualizado de Manera Correcta!")
        .status(HttpStatus.OK.value())
        .data(historial)
        .build();
    }

    
    //Controlador para Eliminar Cualquier Historia clinica incluyendo tambien las Prescripciones Medicas de ese paciente
    @DeleteMapping("/{id}")
    public ServerResponseDataDto eliminarHistoriaClinica(@PathVariable Long id) {

        service.eliminarHistoriaClinica(id);

        return ServerResponseDataDto.builder()
        .message("Historial Clinico Eliminado de Manera Exitosa!")
        .status(HttpStatus.OK.value())
        .data(null)
        .build();
    }
}
