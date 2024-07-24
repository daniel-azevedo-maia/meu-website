package net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String detail) {
        super("User already exists: " + detail);
    }

}
