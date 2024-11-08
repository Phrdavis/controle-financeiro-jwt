package br.com.davipinheirodesouza.controle_financeiro_jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import br.com.davipinheirodesouza.controle_financeiro_jwt.components.JwtUtil;
import br.com.davipinheirodesouza.controle_financeiro_jwt.model.AuthRequest;
import br.com.davipinheirodesouza.controle_financeiro_jwt.model.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil){

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){

        try{

            Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())

            );

            String token = jwtUtil.generateToken(authentication.getName());
            return ResponseEntity.ok(new AuthResponse(token)); 
 
        }catch(AuthenticationException  e){

            return ResponseEntity.status(401).body("Credenciais Inválidas");

        }

    }
    
}
