package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.sena.Clinica.el.Rosal.Entity.HistoriaClinicaEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.PacienteEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.PrescripcionMedicaEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.HistoriaClinicaRepository;
import co.edu.sena.Clinica.el.Rosal.Repository.PacienteRepository;
import co.edu.sena.Clinica.el.Rosal.dto.HistoriaClinicaDTO;
import co.edu.sena.Clinica.el.Rosal.dto.PrescripcionMedicaDTO;

@Service
public class HistoriaClinicaService {


    @Autowired
    private HistoriaClinicaRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    // Este Servicio se Encarga de la Creacion de un Nuevo Historial Clinico para un Paciente en especifico
    @Transactional
    public void save(HistoriaClinicaDTO dto) {

        // Se Valida que el paciente si Exista
        PacienteEntity paciente = pacienteRepository.findById(dto.getIdPaciente())
        .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Se Cambia la Entidad de entity a historia para evitar confunsion a la hora de hacer la PrescripcionMedica y para que historia este definida dentro del for
        HistoriaClinicaEntity historia = new HistoriaClinicaEntity(); 
        historia.setPaciente(paciente); // Relación bidireccional correcta
        historia.setIdMedico(dto.getIdMedico());
        historia.setFechaConsulta(dto.getFechaConsulta());
        historia.setMotivoConsulta(dto.getMotivoConsulta());
        historia.setDiagnostico(dto.getDiagnostico());
        historia.setTratamiento(dto.getTratamiento());
        historia.setAlergias(dto.getAlergias());
        historia.setAntecedentes(dto.getAntecedentes());
        historia.setSignosVitales(dto.getSignosVitales());
        historia.setExamenesSolicitado(dto.getExamenesSolicitado());

        // Se Asocia con la Prescripcion Medica
        for (PrescripcionMedicaDTO prescripcionMedicaDTO : dto.getPrescripcionMedica()) {
            
            PrescripcionMedicaEntity entity = new PrescripcionMedicaEntity();
            entity.setHistoria(historia); // Relación bidireccional correcta
            entity.setIdMedicamentos(prescripcionMedicaDTO.getIdMedicamentos());
            entity.setCantidadTotal(prescripcionMedicaDTO.getCantidadTotal());
            entity.setPresentacion(prescripcionMedicaDTO.getPresentacion());
            entity.setIndicaciones(prescripcionMedicaDTO.getIndicaciones());
    
            // Agregar la prescripción al historial clínico
            historia.getPrescripcionMedica().add(entity);
        }

       repository.save(historia);
    }


    // Se Crea el Servicio para Obtener las Historias clinicas de un paciente en especifico de la mas reciente a la mas antigua
    public List<HistoriaClinicaDTO> HistoriasClinicasByIdPaciente(Long idPaciente) {

        // Se Valida que el paciente si Exista
        PacienteEntity paciente = pacienteRepository.findById(idPaciente)
        .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Se pide Obtener las Historias Clinicas del paciente y que se Ordene por su fecha de consulta
        List<HistoriaClinicaEntity> historia = repository.findByPacienteOrderByFechaConsultaAsc(paciente);

        // Se hace la Verificacion de que si el paciente cuenta con Historia Clinica
        if ( historia.isEmpty()) {
            System.out.println("No se encontraron historias clínicas para el paciente con ID:" + idPaciente);
        }

        // Se Obteniene las historias clínicas del paciente que se ordenan por fecha de consulta
        return historia.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    }

    // Se Convierte las Historias Clinicas a DTO
    private HistoriaClinicaDTO convertToDTO(HistoriaClinicaEntity historia) {
       
        HistoriaClinicaDTO dto = new HistoriaClinicaDTO();
        dto.setId(historia.getId());
        dto.setIdPaciente(historia.getPaciente().getId());
        dto.setIdMedico(historia.getIdMedico());
        dto.setFechaConsulta(historia.getFechaConsulta());
        dto.setMotivoConsulta(historia.getMotivoConsulta());
        dto.setDiagnostico(historia.getDiagnostico());
        dto.setTratamiento(historia.getTratamiento());
        dto.setAlergias(historia.getAlergias());
        dto.setAntecedentes(historia.getAntecedentes());
        dto.setSignosVitales(historia.getSignosVitales());
        dto.setExamenesSolicitado(historia.getExamenesSolicitado());

       // Se Convierte las Prescripciones Medicas a DTO
        List<PrescripcionMedicaDTO> prescripcionesDTO = historia.getPrescripcionMedica().stream()
        .map(prescripcion -> {
            PrescripcionMedicaDTO prescripcionDTO = new PrescripcionMedicaDTO();
            prescripcionDTO.setId(prescripcion.getId());
            prescripcionDTO.setIdMedicamentos(prescripcion.getIdMedicamentos());
            prescripcionDTO.setCantidadTotal(prescripcion.getCantidadTotal());
            prescripcionDTO.setPresentacion(prescripcion.getPresentacion());
            prescripcionDTO.setIndicaciones(prescripcion.getIndicaciones());
            return prescripcionDTO;
        })
        .collect(Collectors.toList()); // Se encargaria de Recopilar todos los objetos DTO de la lista.

        dto.setPrescripcionMedica(prescripcionesDTO); // Asigna la lista de DTO de prescripciones médicas al objeto "dto".
        return dto; // Retorna el DTO que fue transformado
    }


    // Se Crea el Servicio con el fin de poder obtener todas las Historias Clinicas junto a las Prescripciones medicas de todos los pacientes
    public List<HistoriaClinicaDTO> obtenerTodasLasHistoriasClinicas() {
        
        return repository.findAll().stream() //En este caso se obtiene la lista de todas las entidades de la historias clínicas almacenadas dentro de la BD
        .map(this::convertToDTO)
        .collect(Collectors.toList()); // Se Recolecta todas las listas de objetos de los DTO generados
    }


    // Se Crea el Servicio para Actualizar una Historia Clinica junto a su prescripcion medica de un paciente
    @Transactional
    public HistoriaClinicaDTO actualizarHistoriaClinica(Long id, HistoriaClinicaDTO dto) {

        HistoriaClinicaEntity historia = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encuentra registro de una Historia Clinica"));

        // Se Actualiza cada uno de los campos ingresados de la Historia Clinica
        historia.setMotivoConsulta(dto.getMotivoConsulta());
        historia.setDiagnostico(dto.getDiagnostico());
        historia.setTratamiento(dto.getTratamiento());
        historia.setAlergias(dto.getAlergias());
        historia.setAntecedentes(dto.getAntecedentes());
        historia.setSignosVitales(dto.getSignosVitales());
        historia.setExamenesSolicitado(dto.getExamenesSolicitado());

        // Se Eliminaria de igual manera las Prescripciones Medicas Existentes
        historia.getPrescripcionMedica().clear();

        // Se Agregan nuevamente los Campos de Prescripcion Medica para llenar los nuevos medicamentos que fueron recetados
        for (PrescripcionMedicaDTO prescripcionMedicaDTO : dto.getPrescripcionMedica()) {
            
            PrescripcionMedicaEntity entity = new PrescripcionMedicaEntity();
            entity.setHistoria(historia); // Relación bidireccional correcta
            entity.setIdMedicamentos(prescripcionMedicaDTO.getIdMedicamentos());
            entity.setCantidadTotal(prescripcionMedicaDTO.getCantidadTotal());
            entity.setPresentacion(prescripcionMedicaDTO.getPresentacion());
            entity.setIndicaciones(prescripcionMedicaDTO.getIndicaciones());
    
            // Agregar la prescripción al historial clínico
            historia.getPrescripcionMedica().add(entity);
        }

        repository.save(historia);
        return convertToDTO(historia);
    }

    
    // Se Obtuvo este Servicio para poder Eliminar La Historia Clinica junto a su Prescripcion medica de un paciente
    @Transactional
    public void eliminarHistoriaClinica(Long id) {
        
        HistoriaClinicaEntity historia = repository.findById(id) // Se intentaria encontrar la Historia Clínica dentro de la BD por medio de su ID
        .orElseThrow(() -> new RuntimeException("La Historia Clinica no fue encontrada dentro del sistema"));
        repository.delete(historia); // Una vez que se tiene la entidad de la Historia Clínica, se elimina del repositorio y tambien su BD
    }
}
