package co.edu.sena.Clinica.el.Rosal.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se asegura que se genera automáticamente
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "login",nullable = false, unique = true)
    private String login;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "id_auxiliar")
    private Long idAuxiliar;

    @Column(name = "id_farmaceutico")
    private Long idFarmaceutico;

    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "codigo_restablecimiento")
    private String codigoRestablecimiento;

    @Column(name = "expiracion_codigo")
    private LocalDateTime expiracionCodigo;

    @Column(name = "ultima_solicitud")
    private LocalDateTime ultimaSolicitud;

    @Column(name = "intentos_fallidos")
    private int intentosFallidos;
}
