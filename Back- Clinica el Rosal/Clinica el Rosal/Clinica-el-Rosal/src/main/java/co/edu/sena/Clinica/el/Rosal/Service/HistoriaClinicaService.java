package co.edu.sena.Clinica.el.Rosal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.HistoriaClinicaEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.HistoriaClinicaRepository;
import co.edu.sena.Clinica.el.Rosal.dto.HistoriaClinicaDTO;

@Service
public class HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository repository;

    // Este Servicio se Encarga de la Creacion de un Nuevo Historial Clinico
    public void save(HistoriaClinicaDTO dto) {

        HistoriaClinicaEntity entity = new HistoriaClinicaEntity();
        entity.setIdPaciente(dto.getIdPaciente());
        entity.setIdMedico(dto.getIdMedico());
        entity.setFechaConsulta(dto.getFechaConsulta());
        entity.setDiagnostico(dto.getDiagnostico());
        entity.setTratamiento(dto.getTratamiento());
        entity.setAlergias(dto.getAlergias());
        entity.setAntecedentes(dto.getAntecedentes());
        entity.setSignosVitales(dto.getSignosVitales());
        entity.setExamenesSolicitado(dto.getExamenesSolicitado());

        repository.save(entity);
    }


}
