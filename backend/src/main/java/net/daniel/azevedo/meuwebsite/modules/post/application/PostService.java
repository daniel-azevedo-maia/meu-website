package net.daniel.azevedo.meuwebsite.modules.post.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Post;
import net.daniel.azevedo.meuwebsite.modules.post.domain.repositories.PostRepository;
import net.daniel.azevedo.meuwebsite.modules.post.dto.CreatePostDTO;
import net.daniel.azevedo.meuwebsite.modules.post.dto.UpdatePostDTO;
import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;
import net.daniel.azevedo.meuwebsite.modules.user.domain.repositories.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Transactional
    public Post create(CreatePostDTO createPostDTO) {
        User user = userRepository.findById(createPostDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User %s not found", createPostDTO.getUserId())));

        Post post = new Post();
        post.setUser(user);
        post.setTitulo(createPostDTO.getTitulo());
        post.setSubtitulo(createPostDTO.getSubtitulo());
        post.setTexto(createPostDTO.getTexto());
        post.setUrlImagem(createPostDTO.getUrlImagem());
        post.setCategoria(createPostDTO.getCategoria());
        post.setDataHoraCriacao(LocalDateTime.now());
        post.setAtualizacao(LocalDateTime.now());

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Post %s not found", postId)));
    }

    @Transactional
    public void deleteById(Long postId) {
        Post post = findById(postId);
        postRepository.delete(post);
    }

    @Transactional
    public Post update(Long postId, UpdatePostDTO updatePostDTO) {
        Post post = findById(postId);
        post.setTitulo(updatePostDTO.getTitulo());
        post.setSubtitulo(updatePostDTO.getSubtitulo());
        post.setTexto(updatePostDTO.getTexto());
        post.setUrlImagem(updatePostDTO.getUrlImagem());
        post.setCategoria(updatePostDTO.getCategoria());
        post.setAtualizacao(LocalDateTime.now());
        return postRepository.save(post);
    }

}
