package net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super("Invalid user data: " + message);
    }
}