package co.edu.sena.Clinica.el.Rosal.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.sena.Clinica.el.Rosal.Entity.FacturacionEntity;
import co.edu.sena.Clinica.el.Rosal.Service.FacturacionService;
import co.edu.sena.Clinica.el.Rosal.dto.FacturacionDto;
import co.edu.sena.Clinica.el.Rosal.dto.ServerResponseDataDto;

@RestController
@RequestMapping("/facturacion")
public class FacturacionController {

   @Autowired
    private FacturacionService service;

    // Este Controlador funciona para ejecutar la Creacion de una nueva Factura 
    @PostMapping()
    @PreAuthorize("hasRole('AUXILIAR')") // Se crea una restriccion para que solo el auxiliar pueda hacer este proceso 
    public ServerResponseDataDto create(@RequestBody FacturacionDto request) { // Cumple la funcion de recibir la solicitud al postman con los datos del cuerpo de la notacion requestbody para que procese los datos 

        service.save(request); // Se le llama al servicio para guardar la informacion del dto a la base de datos 

        return ServerResponseDataDto.builder()
        .message("Se ha Generado una nueva Facturacion correctamente!")
        .status(HttpStatus.OK.value())
        .data(null)
        .build();
    }

    // Este Controlador cumple la funcion de poder obtener todas las Facturas generadas
    @GetMapping()
    @PreAuthorize("hasRole('AUXILIAR')")
    public List<FacturacionEntity> obtenerTodasLasFacturas() {  // Se cumple la funcion de recibir la solicitud del listado al postman o cualquier usuario con el HTTP para obtener todas las facturas almacenadas en la base de datos.
        return service.obtenerTodasLasFacturas(); // Llama al servicio para recuperar y retornar la lista de facturas.
    }

    // Este Controlador cumple con poder obtener facturas por medio de su ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('AUXILIAR')")
    public Optional<FacturacionEntity> obtenerFacturaPorId(@PathVariable Long id) {  // Verifica si el ID de la factura existe en la base de datos
        return service.obtenerFacturaPorId(id);
    }

    // Actualiza la Factura por medio de su ID
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUXILIAR')")
    public ServerResponseDataDto update(
        @PathVariable("id") Long id, 
        @RequestBody FacturacionDto request) {

        request.setId(id);
        request = this.service.update(request);

        return ServerResponseDataDto
        .builder()
        .message(request != null ? "Actualizacion de la Factura de manera correcta" : "No se pudo Actualizar la factura intente Nuevamente")
        .status(request != null ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value() )
        .data(request)
        .build();
    }

    // Se Ellimina una Factura por intermedio de su ID Existente
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('AUXILIAR')")
    public ServerResponseDataDto deleteById(@PathVariable("id") Long id ) {

        this.service.delete(id);

        return ServerResponseDataDto
        .builder()
        .message("Factura Eliminada Exitosamente")
        .status( HttpStatus.OK.value())
        .build();
    }


}
