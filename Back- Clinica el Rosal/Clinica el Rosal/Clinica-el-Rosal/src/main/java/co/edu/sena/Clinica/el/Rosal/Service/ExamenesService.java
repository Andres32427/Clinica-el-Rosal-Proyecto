package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.ExamenesEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.ExamenesRepository;
import co.edu.sena.Clinica.el.Rosal.dto.ExamenesDto;

@Service
public class ExamenesService {

    @Autowired
    private ExamenesRepository repository;

    // Se Crea el Servicio para un Nuevo Examen medico
    public void save(ExamenesDto dto) {

        ExamenesEntity entity = new ExamenesEntity();
        
        entity.setIdTipoExamen(dto.getIdTipoExamen());
        entity.setFechaExamen(dto.getFechaExamen());
        entity.setArchivoExamen(dto.getArchivoExamen());
        entity.setIdPaciente(dto.getIdPaciente());
        entity.setIdAuxiliar(dto.getIdAuxiliar());
        entity.setCreatedAt(dto.getCreatedAt());

        repository.save(entity);
    }

    // Se Ha Creado un servicio para Obtener los examen medico por Paciente dentro de su perfil
    public List<ExamenesEntity> obtenerExamen(Long idPaciente) {
        return repository.findByIdPaciente(idPaciente);
    }

    // En esta instancia se Creo el servicio para recoger todos los examenes medicos encontrados en el sistema
    public List<ExamenesEntity> obtenerTodosLosExamenes() { 
        return repository.findAll();
    }

    // Este servicio es el que funciona para gestionar dependiendo de cada auxiliar su envio de examen al respectivo paciente
    public List<ExamenesEntity> obtenerExamenSubido(Long idAuxiliar) {
        return repository.findByIdAuxiliar(idAuxiliar);
    }

    //Este Servicio Actualiza cada uno de los Examenes medicos
    public ExamenesDto update(ExamenesDto newData) {

        Optional<ExamenesEntity> OptionalExamenes = this.repository.findById(newData.getId()); // Verifica si existe ese ID en la Base de datos 

        if (OptionalExamenes.isPresent()) {       // Si se Cumple que el Id del examen se cumple se llena la informacion 
            ExamenesEntity entity = OptionalExamenes.get();

            // Si lo anterior sale Correcto se llena toda esta informacion con los nuevos datos actualizado
            entity.setId(newData.getId());
            entity.setIdTipoExamen(newData.getIdTipoExamen());
            entity.setFechaExamen(newData.getFechaExamen());
            entity.setArchivoExamen(newData.getArchivoExamen());
            entity.setIdPaciente(newData.getIdPaciente());
            entity.setIdAuxiliar(newData.getIdAuxiliar());
            entity.setCreatedAt(newData.getCreatedAt());

            this.repository.save(entity);  // Se guarda la Informacion dentro de la base de datos 

            return newData; // Devuelve los datos Actuallizado de manera eficiente
        }

        return null; // Si no se encuentra el examen se enviara un mensaje al postman que es null 
    }

    // Se Elimina el Examen medico de la base de datos con este Servicio 
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
