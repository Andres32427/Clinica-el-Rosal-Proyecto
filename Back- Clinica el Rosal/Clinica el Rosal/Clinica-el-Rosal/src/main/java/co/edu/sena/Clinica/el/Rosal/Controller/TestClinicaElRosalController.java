package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Service.PersonaService;
import co.edu.sena.Clinica.el.Rosal.dto.PersonaDto;

@RestController
@RequestMapping("/TestClinicaElRosal")
public class TestClinicaElRosalController {

    @Autowired
    private PersonaService service;




    @GetMapping()
    public String BienvenidoalaclinicaelRosal(){
        
        PersonaDto dto = new PersonaDto();
        dto.setNombre("Wilches");
        dto.setApellido("Ochoa");
        dto.setGenero("M");
        dto.setFechaNacimiento(new Date());
        dto.setTipoIdentificacion("Registro Civil");
        dto.setIdentificacion("109847047");
        dto.setIdSeguro("10");
        dto.setTelefono("320614910");
        dto.setCorreo("Federico34@gmail.com");
        dto.setDireccion("Calle40#7e-25");
        dto.setGrupoSanguineo("AB-");
        dto.setAlergias("No");
        dto.setTipoDeAlergia("N/A");
        dto.setIdMunicipio(1095L);

        service.save(dto);
        
        return "Bienvenido a la clinica el Rosal";
    }

    @GetMapping("/Salida")
    public String SalidaDeLaClinicaElRosal(){
        return "Espero que haya Tenido Una Buena Experiencia Dentro De La clinica";
    }
}
