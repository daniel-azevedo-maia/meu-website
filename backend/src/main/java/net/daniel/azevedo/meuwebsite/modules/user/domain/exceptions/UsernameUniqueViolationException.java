package net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions;


public class UsernameUniqueViolationException extends RuntimeException {

    public UsernameUniqueViolationException(String message) {
        super("User already exists!");
    }
}
