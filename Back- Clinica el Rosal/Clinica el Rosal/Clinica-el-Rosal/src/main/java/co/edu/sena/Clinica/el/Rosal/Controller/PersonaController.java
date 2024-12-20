package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.PersonaService;
import co.edu.sena.Clinica.el.Rosal.dto.PersonaDto;
import co.edu.sena.Clinica.el.Rosal.dto.ServerResponseDataDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Persona")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @PostMapping()
    public ServerResponseDataDto create(@RequestBody PersonaDto request){


        service.save(request);

        return ServerResponseDataDto.builder()
        .message("Registro exitoso!")
        .status(HttpStatus.OK.value())
        .data(null)
        .build();
    }

    
    @GetMapping()
    public ServerResponseDataDto listALL() {
        
        List<PersonaDto> dtos = this.service.getAll();

        return ServerResponseDataDto.builder()
        .message("Consulta Exitosa!")
        .status(HttpStatus.OK.value())
        .data(dtos)
        .build();
    }
}
