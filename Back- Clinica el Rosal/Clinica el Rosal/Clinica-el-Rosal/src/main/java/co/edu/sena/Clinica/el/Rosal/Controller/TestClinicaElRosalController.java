package co.edu.sena.Clinica.el.Rosal.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TestClinicaElRosal")
public class TestClinicaElRosalController {



    @GetMapping()
    public String BienvenidoalaclinicaelRosal(){
        return "Bienvenido a la clinica el Rosal";
    }

    @GetMapping("/Salida")
    public String SalidaDeLaClinicaElRosal(){
        return "Espero que haya Tenido Una Buena Experiencia Dentro De La clinica";
    }
}
