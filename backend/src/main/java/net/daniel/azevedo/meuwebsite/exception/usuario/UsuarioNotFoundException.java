package net.daniel.azevedo.meuwebsite.exception.usuario;

public class UsuarioNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsuarioNotFoundException(Long id) {
        super("Usuário com id " + id + " não encontrado.");
    }

}
