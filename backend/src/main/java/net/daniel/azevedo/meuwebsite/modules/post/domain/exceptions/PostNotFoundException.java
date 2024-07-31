package net.daniel.azevedo.meuwebsite.modules.post.domain.exceptions;

public class PostNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PostNotFoundException(String aux) {
        super("Post with filter " + aux + " not found.");
    }

    public PostNotFoundException(Long postId) {
        super("Post with ID " + postId + " not found.");
    }

}
