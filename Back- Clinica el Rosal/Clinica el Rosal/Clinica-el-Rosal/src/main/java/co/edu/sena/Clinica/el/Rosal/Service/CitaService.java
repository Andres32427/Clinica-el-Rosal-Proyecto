package co.edu.sena.Clinica.el.Rosal.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.CitaEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.CitaRepository;
import co.edu.sena.Clinica.el.Rosal.dto.CitaDto;

@Service
public class CitaService {

    @Autowired
    private CitaRepository repository;

    // Se Obtiene todas las Citas agendada dentro del sistema 
    public List<CitaEntity> obtenerTodasLasCitas(){
        return repository.findAll();
    }

    // Ayuda a Obtener Citas por medio de su ID
    public CitaEntity obtenerCitaPorId(Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cita No Encontrada"));
    }


    // se Gestiona la Creacion de la Cita 
    public void save(CitaDto dto) {

        CitaEntity entity = new CitaEntity();
        entity.setIdPaciente(dto.getIdPaciente());
        entity.setIdMedico(dto.getIdMedico());
        entity.setFecha(dto.getFecha());
        entity.setHora(dto.getHora());
        entity.setEstado(dto.getEstado());
        entity.setIdEspecialidad(dto.getIdEspecialidad());

        // Es Necesario extraer estos valores para poder validar si estan disponibles o no 
        Long idMedico = dto.getIdMedico();
        LocalDate fecha = dto.getFecha();
        LocalTime hora = dto.getHora();

        if (repository.existsByIdMedicoAndFechaAndHora(idMedico, fecha, hora)) {
            throw new RuntimeException("EL MEDICO YA TIENE UNA CITA PROGRAMADA PARA ESA HORA INGRESE NUEVAMENTE OTRO HORARIO"); 
        }

        repository.save(entity);
    }

    // Se Utiliza Para poder Actualizar o hacer Update de la cita ya asignada anteriormente
    public CitaDto actualizarCita(CitaDto newData) {
        System.out.println("ID recibido: " + newData.getId());

        Optional<CitaEntity> OptionalCita = this.repository.findById(newData.getId());

        if (OptionalCita.isEmpty()) {
            System.out.println("No se encontró la cita con ID: " + newData.getId());

            throw new RuntimeException("LA CITA NO EXISTE ASI QUE NO SE PUEDE ACTUALIZAR");
        }

        System.out.println("Cita encontrada, procediendo con la actualización.");

        // Es Necesario extraer estos valores para poder validar si estan disponibles o no 
        Long idMedico = newData.getIdMedico();
        LocalDate fecha = newData.getFecha();
        LocalTime hora = newData.getHora();

        System.out.println("Validando disponibilidad del médico ID: " + idMedico + " en fecha: " + fecha + " y hora: " + hora);
        
        if (repository.existsByIdMedicoAndFechaAndHora(idMedico, fecha, hora)) {
            System.out.println("Conflicto: El médico ya tiene una cita en ese horario.");

            throw new RuntimeException("EL MÉDICO YA TIENE UNA CITA PROGRAMADA PARA ESA HORA. INGRESE OTRO HORARIO.");
        }

        CitaEntity entity = OptionalCita.get();
        
        entity.setId(newData.getId());
        entity.setIdPaciente(newData.getIdPaciente());
        entity.setIdMedico(newData.getIdMedico());
        entity.setFecha(newData.getFecha());
        entity.setHora(newData.getHora());
        entity.setEstado(newData.getEstado());
        entity.setIdEspecialidad(newData.getIdEspecialidad());

        System.out.println("Guardando la cita actualizada...");

        this.repository.save(entity);

        System.out.println("Cita actualizada exitosamente.");
        
        return newData;
    }

    // Se Utiliza Para poder Reprogramar la cita ya asignada anteriormente
    public CitaDto reprogramarCita(Long id, CitaDto dto) {
        System.out.println("ID recibido para reprogramación: " + id);

        Optional<CitaEntity> OptionalCita = this.repository.findById(id);

        if (OptionalCita.isEmpty()) {
            System.out.println("NO SE ENCONTRO LA CITA CON SU ID" + id);
            throw new RuntimeException("LA CITA NO EXISTE ASI QUE NO SE PUEDE REPROGRAMAR");
        }

        System.out.println("Cita encontrada, procediendo con su Reprogramacion.");

        CitaEntity entity = OptionalCita.get();

        Long idMedico = dto.getIdMedico();
        LocalDate fecha = dto.getFecha();
        LocalTime hora = dto.getHora();

        System.out.println("Validando disponibilidad del médico ID: " + idMedico + " en fecha: " + fecha + " y hora: " + hora);

        if (repository.existsByIdMedicoAndFechaAndHora(idMedico, fecha, hora)) {
            System.out.println("Conflicto: El médico ya tiene una cita en ese horario.");

            throw new RuntimeException("EL MÉDICO CON ID " + idMedico + " YA TIENE UNA CITA EN ESA HORA, INGRESE OTRO HORARIO.");
        }

        // Se le agrega los Nuevos datos de fecha, hora y estado para su reprogramacion Sin que se cruce con sus otras Consultas
        entity.setFecha(fecha);
        entity.setHora(hora);
        entity.setEstado("REPROGRAMAR");

        System.out.println("Guardando Su cita reprogramada...");
        this.repository.save(entity);

        System.out.println("Cita reprogramada exitosamente.");

        return dto;
    } 

    // Este servicio va dirigido a la cancelacion de la cita Medica asignada 
    public CitaDto cancelarCita(Long id, CitaDto dto) {
        System.out.println("ID recibido para cancelación: " + id);

        Optional<CitaEntity> OptionalCita = this.repository.findById(id);

        if (OptionalCita.isEmpty()) {
            System.out.println("No se encontró la cita con ID: " + id);

            throw new RuntimeException("LA CITA NO EXISTE POR ENDE NO SE PUEDE CANCELAR");
        }

        CitaEntity entity = OptionalCita.get();

        entity.setEstado("CANCELAR");

        System.out.println("GUARDANDO LA CANCELACION DE LA CITA");

        this.repository.save(entity);

        System.out.println("CITA CANCELADA DE MANERA EXITOSA");

        return dto;
    }

    // Este Servicio elimina la Cita de Manera Automatica
    public void eliminarCita(Long id) {
        this.repository.deleteById(id);
    }
    
}
