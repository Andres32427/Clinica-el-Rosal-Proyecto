package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.dto.PacienteDto;
import co.edu.sena.Clinica.el.Rosal.Entity.PacienteEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository Repository;

    public void save (PacienteDto dto){

        PacienteEntity entity = new PacienteEntity();
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

    public List<PacienteDto> getAll(){

        List<PacienteDto> dtos = new ArrayList<>();
        List<PacienteEntity> entities = Repository.findAll();

        for (PacienteEntity entity : entities) {
            PacienteDto dto = new PacienteDto();

            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setApellido(entity.getApellido());
            dto.setGenero(entity.getGenero());
            dto.setFechaNacimiento(entity.getFechaNacimiento());
            dto.setTipoIdentificacion(entity.getTipoIdentificacion());
            dto.setIdentificacion(entity.getIdentificacion());
            dto.setIdSeguro(entity.getIdSeguro());
            dto.setTelefono(entity.getTelefono());
            dto.setCorreo(entity.getCorreo());
            dto.setDireccion(entity.getDireccion());
            dto.setGrupoSanguineo(entity.getGrupoSanguineo());
            dto.setAlergias(entity.getAlergias());
            dto.setTipoDeAlergia(entity.getTipoDeAlergia());
            dto.setIdMunicipio(entity.getIdMunicipio());    

            dtos.add(dto);
        }
       
        return dtos;
    }

    public PacienteDto getById(long id){

        Optional<PacienteEntity> optionalPaciente = this.Repository.findById(id);

        if (optionalPaciente.isPresent()) {
           PacienteEntity entity = optionalPaciente.get();
           PacienteDto dto = new PacienteDto();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setApellido(entity.getApellido());
            dto.setGenero(entity.getGenero());
            dto.setFechaNacimiento(entity.getFechaNacimiento());
            dto.setTipoIdentificacion(entity.getTipoIdentificacion());
            dto.setIdentificacion(entity.getIdentificacion());
            dto.setIdSeguro(entity.getIdSeguro());
            dto.setTelefono(entity.getTelefono());
            dto.setCorreo(entity.getCorreo());
            dto.setDireccion(entity.getDireccion());
            dto.setGrupoSanguineo(entity.getGrupoSanguineo());
            dto.setAlergias(entity.getAlergias());
            dto.setTipoDeAlergia(entity.getTipoDeAlergia());
            dto.setIdMunicipio(entity.getIdMunicipio());
            return dto;  
        }

        return null;
    }

    public void delete(Long id){
        this.Repository.deleteById(id);
    }


    public PacienteDto update(PacienteDto newData){

        Optional<PacienteEntity> optionalPaciente = this.Repository.findById(newData.getId());

        if (optionalPaciente.isPresent()) {
            PacienteEntity entity = optionalPaciente.get();

            entity.setId(newData.getId());
            entity.setNombre(newData.getNombre());
            entity.setApellido(newData.getApellido());
            entity.setGenero(newData.getGenero());
            entity.setFechaNacimiento(newData.getFechaNacimiento());
            entity.setTipoIdentificacion(newData.getTipoIdentificacion());
            entity.setIdentificacion(newData.getIdentificacion());
            entity.setIdSeguro(newData.getIdSeguro());
            entity.setTelefono(newData.getTelefono());
            entity.setCorreo(newData.getCorreo());
            entity.setDireccion(newData.getDireccion());
            entity.setGrupoSanguineo(newData.getGrupoSanguineo());
            entity.setAlergias(newData.getAlergias());
            entity.setTipoDeAlergia(newData.getTipoDeAlergia());
            entity.setIdMunicipio(newData.getIdMunicipio());

            this.Repository.save(entity);

            return newData;
        }

        return null;
    }




}
