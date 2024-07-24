package net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super("Usuário com id " + id + " não encontrado.");
    }

}
