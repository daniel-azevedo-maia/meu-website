package net.daniel.azevedo.meuwebsite.service;

import net.daniel.azevedo.meuwebsite.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

}
