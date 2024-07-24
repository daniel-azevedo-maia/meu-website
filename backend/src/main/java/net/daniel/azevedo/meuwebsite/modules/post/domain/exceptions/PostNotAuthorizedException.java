package net.daniel.azevedo.meuwebsite.modules.post.domain.exceptions;

public class PostNotAuthorizedException extends RuntimeException {
    public PostNotAuthorizedException() {
        super("User is not authorized to modify this post.");
    }
}