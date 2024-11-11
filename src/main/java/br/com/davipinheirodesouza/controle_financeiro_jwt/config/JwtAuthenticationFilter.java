package br.com.davipinheirodesouza.controle_financeiro_jwt.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.davipinheirodesouza.controle_financeiro_jwt.components.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil){

        this.jwtUtil = jwtUtil;

    }

    @Override 
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){

            String token = authorizationHeader.substring(7);

            try{

                String username = jwtUtil.extractUsername(token);

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
                    
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    
                }


            }catch(JWTVerificationException  e){

                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT Inválido ou Expirado!");
                return;

            }

        }
        
        filterChain.doFilter(request, response);

    }
}
