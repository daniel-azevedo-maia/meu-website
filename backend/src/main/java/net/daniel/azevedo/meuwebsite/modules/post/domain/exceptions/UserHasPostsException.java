package net.daniel.azevedo.meuwebsite.modules.post.domain.exceptions;

public class UserHasPostsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserHasPostsException() {
        super("Este usuário não pode ser excluído, pois tem posts a ele vinculados.");
    }

}
