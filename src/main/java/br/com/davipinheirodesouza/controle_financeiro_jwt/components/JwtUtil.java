package br.com.davipinheirodesouza.controle_financeiro_jwt.components;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "your_secrect_key";

    public String generateToken(String username) {

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(Algorithm.HMAC256(SECRET_KEY));

    }

    public String extractUsername(String token){

        return JWT.decode(token).getSubject();

    }

    public boolean validateToken(String token, String username){

        String extractUsername = extractUsername(token);

        return (extractUsername.equals(username) && !isTokenExpired(token));

    }

    public boolean isTokenExpired(String token){

        return JWT.decode(token).getExpiresAt().before(new Date());

    }
    
}
