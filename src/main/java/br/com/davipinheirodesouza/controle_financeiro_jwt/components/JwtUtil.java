package br.com.davipinheirodesouza.controle_financeiro_jwt.components;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private static final long EXPIRATION_TIME = TimeUnit.HOURS.toMillis(1);

    public String generateToken(String username) {

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
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

    public boolean isValidToken(String token){

        try{

            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            return !isTokenExpired(token);

        }catch(JWTVerificationException e){

            return false;

        }

    }
    
}
