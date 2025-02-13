package co.edu.sena.Clinica.el.Rosal.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.CitaEntity;

@Repository
public interface CitaRepository extends 
    JpaRepository<CitaEntity, Long>,
    JpaSpecificationExecutor<CitaEntity> {

    List<CitaEntity> findByIdMedico(Long idMedico);
    List<CitaEntity> findByIdPaciente(Long idPaciente);
    boolean existsByIdMedicoAndFechaAndHora(Long idMedico, LocalDate fecha, LocalTime hora);

}
