package br.com.codenation.errordashboard.exceptions;

public class InvalidOrExpiredToken extends RuntimeException {

    public InvalidOrExpiredToken() {
        super("Token inválido ou expirado");
    }
}
