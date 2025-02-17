package co.edu.sena.Clinica.el.Rosal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.ExamenesEntity;

@Repository
public interface ExamenesRepository extends 
    JpaRepository<ExamenesEntity, Long>,
    JpaSpecificationExecutor<ExamenesEntity> {

    List<ExamenesEntity> findByIdPaciente(Long idPaciente);
    List<ExamenesEntity> findByIdAuxiliar(Long idAuxiliar);

}
