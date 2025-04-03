package co.edu.sena.Clinica.el.Rosal.Service;

import java.time.LocalDate;
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
    public void save(AgendamientoDto dto) {

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

    // Este Servicio funciona solo con el Auxiliar con el fin de poder obtener los Agendamientos por medicos
    public List<AgendamientoEntity> obtenerAgendamientoMedicoEspecialidadAndFecha(Long idMedico, Long idEspecialidad, LocalDate fecha) {
        return repository.findByIdMedicoAndIdEspecialidadAndFecha(idMedico, idEspecialidad, fecha);
    }

    // Modifica el Agendamiento del Medico con el fin de cambiar cualquier informacion necesaria en base a la consulta pertinente
    public AgendamientoDto modificarAgendamiento(AgendamientoDto newData) {

        Optional<AgendamientoEntity> optionalAgendamiento = this.repository.findById(newData.getId());

        if (optionalAgendamiento.isPresent()) {
            AgendamientoEntity entity = optionalAgendamiento.get();

            boolean horarioOcupado = this.repository.existsByIdMedicoAndFechaAndHora(
                entity.getIdMedico(),
                newData.getFecha(), 
                newData.getHora()
            );

            if (horarioOcupado) {
                throw new RuntimeException("El Horario no esta disponible para el Medico");
            }

            // Se Actualizara solo la Fecha y Hora
            entity.setFecha(newData.getFecha());
            entity.setHora(newData.getHora());

            // Se guarda los nuevos campos en la BD
            this.repository.save(entity);

            return newData;
        }
        return null; // Si no se logra encontrar el agendamiento retorna a null
    }

    // Este Servicio ayuda a bloquear el agendamiento de un paciente
    public AgendamientoEntity bloquearAgendamiento(Long id) {
        AgendamientoEntity entity = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Agendamiento no encontrado"));

        entity.setEstado("Bloqueado");

        return repository.save(entity);
    }

    // Este Servicio ayuda a Liberar el agendamiento de un paciente
    public AgendamientoEntity liberarAgendamiento(Long id) {
        AgendamientoEntity entity = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Agendamiento no encontrado"));

        entity.setEstado("Disponible");

        return repository.save(entity);
    }

    // Este Servicio elimina el Agendamiento de Manera Automatica
    public void eliminarAgendamiento(Long id) {
        this.repository.deleteById(id);
    }
}
