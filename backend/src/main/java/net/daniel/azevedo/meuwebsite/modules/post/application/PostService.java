package net.daniel.azevedo.meuwebsite.modules.post.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Post;
import net.daniel.azevedo.meuwebsite.modules.post.domain.exceptions.PostNotFoundException;
import net.daniel.azevedo.meuwebsite.modules.post.domain.repositories.PostRepository;
import net.daniel.azevedo.meuwebsite.modules.post.dto.CreatePostDTO;
import net.daniel.azevedo.meuwebsite.modules.post.dto.UpdatePostDTO;

import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;
import net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions.UserNotFoundException;
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
                .orElseThrow(() -> new UserNotFoundException(createPostDTO.getUserId()));

        Post post = new Post();
        post.setUser(user);
        post.setTitle(createPostDTO.getTitle());
        post.setSubtitle(createPostDTO.getSubtitle());
        post.setText(createPostDTO.getText());
        post.setImageUrl(createPostDTO.getImageUrl());
        post.setCategory(createPostDTO.getCategory());
        post.setCreationDateTime(LocalDateTime.now());
        post.setUpdateDateTime(LocalDateTime.now());

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
    }

    @Transactional(readOnly = true)
    public List<Post> findPostsByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return postRepository.findAllByUserId(userId);
    }

    @Transactional
    public void deleteById(Long postId) {
        Post post = findById(postId);
        postRepository.delete(post);
    }

    @Transactional
    public Post update(Long postId, UpdatePostDTO updatePostDTO) {
        Post post = findById(postId);
        post.setTitle(updatePostDTO.getTitle());
        post.setSubtitle(updatePostDTO.getSubtitle());
        post.setText(updatePostDTO.getText());
        post.setImageUrl(updatePostDTO.getImageUrl());
        post.setCategory(updatePostDTO.getCategory());
        post.setUpdateDateTime(LocalDateTime.now());
        return postRepository.save(post);
    }

}
