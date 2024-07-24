package net.daniel.azevedo.meuwebsite.modules.post.domain.exceptions;

public class PostNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PostNotFoundException(Long id) {
        super("Post com id " + id + " não encontrado.");
    }

}
