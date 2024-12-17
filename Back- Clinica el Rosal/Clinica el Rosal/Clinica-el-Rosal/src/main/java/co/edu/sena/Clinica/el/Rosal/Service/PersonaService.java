package co.edu.sena.Clinica.el.Rosal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Repository.PersonaRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository Repository;

}
