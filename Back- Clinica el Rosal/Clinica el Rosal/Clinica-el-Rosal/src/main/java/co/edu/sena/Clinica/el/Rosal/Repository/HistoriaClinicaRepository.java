package co.edu.sena.Clinica.el.Rosal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.HistoriaClinicaEntity;
import co.edu.sena.Clinica.el.Rosal.Entity.PacienteEntity;


@Repository
public interface HistoriaClinicaRepository extends
    JpaRepository<HistoriaClinicaEntity, Long>,       
    JpaSpecificationExecutor<HistoriaClinicaEntity> {

    List<HistoriaClinicaEntity> findByPacienteOrderByFechaConsultaAsc(PacienteEntity paciente);

}
