package co.edu.sena.Clinica.el.Rosal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SegurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF (para pruebas con Postman)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/paciente/**").permitAll() // Permite acceso sin autenticación
                .requestMatchers("/agendamiento/**").authenticated() // Requiere autenticación
                .anyRequest().permitAll() // Permite cualquier otra ruta
            )
            .httpBasic(withDefaults()); // Habilita autenticación básica (puedes cambiarlo por JWT)

        return http.build();
    }


}

