package net.daniel.azevedo.meuwebsite.exception.usuario;

public class UsuarioTemPostsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsuarioTemPostsException() {
        super("Este usuário não pode ser excluído, pois tem posts a ele vinculados.");
    }

}
