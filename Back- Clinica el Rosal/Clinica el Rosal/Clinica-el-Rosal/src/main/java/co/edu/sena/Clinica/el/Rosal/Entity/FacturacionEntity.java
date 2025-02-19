package co.edu.sena.Clinica.el.Rosal.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "facturacion")
public class FacturacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se asegura que se genera automáticamente
    @Column (name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_servicio")
    private Long idServicio;

    @Column(name = "monto", precision = 10, scale = 0, nullable = false) // En este caso se almacena el precision y el scale para que solo se almacenen numeros enteros y sin decimales
    private BigDecimal monto; // El BigDecimal fue escogido con el fin de tener mas precision a la hora de almacenar un monto 

    @Column(name = "fecha")
    private LocalDate fecha;
}
