package net.daniel.azevedo.meuwebsite.modules.post.domain.exceptions;

public class PostNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PostNotFoundException(Long postId) {
        super("Post with ID " + postId + " not found.");
    }

}
