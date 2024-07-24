package net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(UUID id) {
        super("Usuário com id " + id + " não encontrado.");
    }

}
