package br.com.davipinheirodesouza.controle_financeiro_jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter){

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }
    
}
