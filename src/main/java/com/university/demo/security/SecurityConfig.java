package com.university.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()  // Permitir acceso sin autenticación
                .requestMatchers("/students").hasRole("ADMIN")
                .anyRequest().authenticated()  // Requerir autenticación para otras rutas
            )
            .formLogin((form) -> form
                .loginPage("/login")  // Página personalizada de login
                //.defaultSuccessUrl("/", true)  // Redirigir a /home después de un login exitoso
                .permitAll()
                //.failureUrl("/login?error") // Rediride a /login con ?error por defeto
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")  // Ruta para logout (por defecto es "/logout")
                .logoutSuccessUrl("/login?logout")  // Redirige a la página de login después del logout
                .deleteCookies("JSESSIONID")
                .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
