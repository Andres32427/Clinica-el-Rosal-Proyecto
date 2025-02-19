package co.edu.sena.Clinica.el.Rosal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.sena.Clinica.el.Rosal.Entity.FacturacionEntity;
import co.edu.sena.Clinica.el.Rosal.Repository.FacturacionRepository;
import co.edu.sena.Clinica.el.Rosal.dto.FacturacionDto;

@Service
public class FacturacionService {

    @Autowired
    private FacturacionRepository repository;

    // Este Servicio Cumple la funcion de Crear Una nueva Facturacion al sistema
    public void save(FacturacionDto dto) {

        FacturacionEntity entity = new FacturacionEntity();
        entity.setIdPaciente(dto.getIdPaciente());
        entity.setIdServicio(dto.getIdServicio());
        entity.setMonto(dto.getMonto());
        entity.setFecha(dto.getFecha());

        repository.save(entity); // Guarda la informacion de la nueva facturacion a la base de datos 
    }

    // Este Otro servicio se va a encargar de obtener las facturas generadas ya dentro de la base de datos
    public List<FacturacionEntity> obtenerTodasLasFacturas() {
        return repository.findAll();
    }

    // Obtiene una factura específica a partir de su ID, si existe.
    public Optional<FacturacionEntity> obtenerFacturaPorId(Long id) { // Verifica si el ID de la factura existe en la base de datos
        return repository.findById(id); // Si existe la retorna si se encuentra presente.
    }

    // Actualiza una factura en especifica eso si Obteniendo por medio de su ID
    public FacturacionDto update(FacturacionDto newData) {

        Optional<FacturacionEntity> OptionalFacturacion = this.repository.findById(newData.getId()); // Intenta recuperar la factura de la base de datos usando el ID proporcionado. 

        if (OptionalFacturacion.isPresent()) {  // Comprueba si la factura con el ID especificado está presente en la base de datos.  

            FacturacionEntity entity = OptionalFacturacion.get(); // Obtiene la entidad si el ID está presente y actualiza su información.  
            entity.setId(newData.getId());
            entity.setIdPaciente(newData.getIdPaciente());
            entity.setIdServicio(newData.getIdServicio());
            entity.setMonto(newData.getMonto());
            entity.setFecha(newData.getFecha());

            this.repository.save(entity);  // Guarda o actualiza la entidad en la base de datos.

            return newData; // Retorna los nuevos datos generados. 
        }

        return null;
    }

    // Se Elimina una factura por medio de su ID
    public void delete(Long id) {
        this.repository.deleteById(id);
    }




}
