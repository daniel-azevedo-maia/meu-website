package net.daniel.azevedo.meuwebsite.modules.post.domain.exceptions;

public class PostAlreadyExistsException extends RuntimeException {
    public PostAlreadyExistsException(String detail) {
        super("Post already exists: " + detail);
    }
}