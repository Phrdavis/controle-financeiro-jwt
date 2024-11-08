package br.com.davipinheirodesouza.controle_financeiro_jwt.model;

public class AuthResponse {

    private String token;

    public AuthResponse(String token){

        this.token = token;

    }

    public String getToken(){
        return token;
    }
    
}
