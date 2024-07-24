package net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class UsernameUniqueViolationException extends RuntimeException {

    public UsernameUniqueViolationException(String message, DataIntegrityViolationException ex) {
        super(message);
    }
}
