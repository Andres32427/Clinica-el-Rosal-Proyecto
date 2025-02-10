package co.edu.sena.Clinica.el.Rosal.Repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.AgendamientoEntity;

@Repository
public interface AgendamientoRepository extends
    JpaRepository<AgendamientoEntity, Long>,
    JpaSpecificationExecutor<AgendamientoEntity> {

    List<AgendamientoEntity> findByIdMedico(Long idMedico);
    List<AgendamientoEntity> findByIdMedicoAndIdEspecialidadAndFecha(Long idMedico, Long idEspecialidad, LocalDate fecha);
}
