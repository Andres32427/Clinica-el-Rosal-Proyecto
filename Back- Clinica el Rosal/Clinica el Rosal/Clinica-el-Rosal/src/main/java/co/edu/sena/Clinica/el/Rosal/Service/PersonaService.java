package co.edu.sena.Clinica.el.Rosal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.dto.PersonaDto;
import co.edu.sena.Clinica.el.Rosal.Entity.PersonaEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.PersonaRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository Repository;

    public void save (PersonaDto dto){

        PersonaEntity entity = new PersonaEntity();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setGenero(dto.getGenero());
        entity.setFechaNacimiento(dto.getFechaNacimiento());
        entity.setTipoIdentificacion(dto.getTipoIdentificacion());
        entity.setIdentificacion(dto.getIdentificacion());
        entity.setIdSeguro(dto.getIdSeguro());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreo(dto.getCorreo());
        entity.setDireccion(dto.getDireccion());
        entity.setGrupoSanguineo(dto.getGrupoSanguineo());
        entity.setAlergias(dto.getAlergias());
        entity.setTipoDeAlergia(dto.getTipoDeAlergia());
        entity.setIdMunicipio(dto.getIdMunicipio());

        Repository.save(entity);
    }
}
