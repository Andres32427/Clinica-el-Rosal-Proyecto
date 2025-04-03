package co.edu.sena.Clinica.el.Rosal;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Se extraen las propiedades del application.yml
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("clinicaelrosal@gmail.com"); // Se logra obtener desde variables de entorno
        mailSender.setPassword("clinica123*"); // Se logra obtener desde variables de entorno

        // Se Hace Propiedades adicionales para la configuracion del correo
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp"); // Se indica el envio de los correos electronicos
        props.put("mail.smtp.auth", "true"); // Ejecuta la Activacion de la Autenticacion
        props.put("mail.smtp.starttls.enable", "true"); // Habilita el STARTTLS para poder obtener conexiones seguras
        props.put("mail.debug", "true"); // Activa logs para depuración

        mailSender.setJavaMailProperties(props);

        return mailSender;
    }
}
