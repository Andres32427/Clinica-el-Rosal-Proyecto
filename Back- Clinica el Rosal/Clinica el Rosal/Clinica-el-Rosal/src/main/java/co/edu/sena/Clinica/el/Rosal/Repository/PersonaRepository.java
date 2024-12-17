package co.edu.sena.Clinica.el.Rosal.Repository;

import co.edu.sena.Clinica.el.Rosal.Entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends
        JpaRepository<PersonaEntity, Long>,
        JpaSpecificationExecutor<PersonaEntity> {

}
