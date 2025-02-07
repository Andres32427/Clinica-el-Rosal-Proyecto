package co.edu.sena.Clinica.el.Rosal.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.AgendamientoEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.AgendamientoRepository;
import co.edu.sena.Clinica.el.Rosal.dto.AgendamientoDto;

@Service
public class AgendamientoService {

    @Autowired
    private AgendamientoRepository repository;

    // este Servicio su Funcion principal es de Gestionar la creacion de un agendamiento en la base de datos
    public void save (AgendamientoDto dto) {

        AgendamientoEntity entity = new AgendamientoEntity();
        entity.setFecha(dto.getFecha());
        entity.setHora(dto.getHora());
        entity.setIdPaciente(dto.getIdPaciente());
        entity.setIdMedico(dto.getIdMedico());
        entity.setIdEspecialidad(dto.getIdEspecialidad());
        entity.setSede(dto.getSede());
        entity.setEstado(dto.getEstado());
        entity.setMotivo(dto.getMotivo());
        entity.setIdUsuarioCreador(dto.getIdUsuarioCreador());
        entity.setTipoCreador(dto.getTipoCreador());

        repository.save(entity);
    }

    // Se Obtiene el Agendamiento del Medico
    public List<AgendamientoEntity> obtenerAgendamientoMedico(Long idMedico) {
        return repository.findByIdMedico(idMedico);
    }

    // Este Servicio funciona solo con el Auxiliar con el fin de poder obtener todos los agendamientos 
    public List<AgendamientoEntity> obtenerTodosLosAgendamientos(){
        return repository.findAll();
    }


    // Modifica el Agendamiento del Medico con el fin de cambiar cualquier informacion necesaria en base a la consulta pertinente
    public AgendamientoDto modificarAgendamiento(AgendamientoDto newData){
        
        Optional<AgendamientoEntity> optionalAgendamiento = this.repository.findById(newData.getId());

        if (optionalAgendamiento.isPresent()) {
            AgendamientoEntity entity = optionalAgendamiento.get();

            entity.setId(newData.getId());
            entity.setFecha(newData.getFecha());
            entity.setHora(newData.getHora());
            entity.setIdPaciente(newData.getIdPaciente());
            entity.setIdMedico(newData.getIdMedico());
            entity.setIdEspecialidad(newData.getIdEspecialidad());
            entity.setSede(newData.getSede());
            entity.setEstado(newData.getEstado());
            entity.setMotivo(newData.getMotivo());
            entity.setIdUsuarioCreador(newData.getIdUsuarioCreador());
            entity.setTipoCreador(newData.getTipoCreador());

            this.repository.save(entity);

            return newData;
        }
        return null;
    }

    // Este Servicio elimina el Agendamiento de Manera Automatica
    public void eliminarAgendamiento(Long id){
        this.repository.deleteById(id);
    }
}
