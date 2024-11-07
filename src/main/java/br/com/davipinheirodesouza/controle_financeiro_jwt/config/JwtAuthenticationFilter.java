package br.com.davipinheirodesouza.controle_financeiro_jwt.config;

import org.springframework.web.filter.OncePerRequestFilter;

import br.com.davipinheirodesouza.controle_financeiro_jwt.components.JwtUtil;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil){

        this.jwtUtil = jwtUtil;

    }
}
