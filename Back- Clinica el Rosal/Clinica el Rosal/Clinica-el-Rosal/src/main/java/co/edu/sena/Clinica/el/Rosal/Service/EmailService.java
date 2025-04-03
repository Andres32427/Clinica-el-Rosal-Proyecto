package co.edu.sena.Clinica.el.Rosal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        
        try {
            // Se hace la Creacion del correo electronico
            MimeMessage message = mailSender.createMimeMessage(); // En este caso Se Crea el mensaje del correo basado en MimeMessage
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); // Ayuda con la creacion del correo y ademas permite que se pueda incluir en formato HTML

            helper.setTo(to); // Define al destinatario que recibira el correo
            helper.setSubject(subject); // Se estableceria el asunto del correo 
            helper.setText(body, true); // Agrega el contenido del correo correspondiente a ser enviado
            helper.setFrom("clinicaelrosal@gmail.com"); // Remitente Obligatorio

            mailSender.send(message); // Envia el Correo electronico al destinatario
            System.out.println("Correo enviado con éxito a: " + to);

            // Envia un error y lanza una excepcion con un mensaje detallado del problema que fue ocurrido
        } catch (Exception e ) {
            throw new RuntimeException("Se Produjo un Error en el envio del correo:" + e.getMessage(), e);
        }
    }
}
