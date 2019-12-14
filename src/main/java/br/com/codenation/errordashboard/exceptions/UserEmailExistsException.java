package br.com.codenation.errordashboard.exceptions;


public class UserEmailExistsException extends RuntimeException {

    public UserEmailExistsException() {
        super("Email informado já está sendo usado");
    }
}
