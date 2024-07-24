package net.daniel.azevedo.meuwebsite.modules.post.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.daniel.azevedo.meuwebsite.modules.post.application.PostService;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Post;
import net.daniel.azevedo.meuwebsite.modules.post.dto.CreatePostDTO;
import net.daniel.azevedo.meuwebsite.modules.post.dto.ListPostsDTO;
import net.daniel.azevedo.meuwebsite.modules.post.dto.PostResponseDTO;
import net.daniel.azevedo.meuwebsite.modules.post.dto.UpdatePostDTO;
import net.daniel.azevedo.meuwebsite.modules.post.mapper.PostMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping
    public ResponseEntity<List<ListPostsDTO>> getAll() {
        List<Post> posts = postService.getAll();
        return ResponseEntity.ok(postMapper.toListPostsDto(posts));
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> create(@RequestBody @Valid CreatePostDTO createPostDTO) {
        Post post = postService.create(createPostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.toPostResponseDto(post));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> findById(@PathVariable Long postId) {
        Post post = postService.findById(postId);
        return ResponseEntity.ok(postMapper.toPostResponseDto(post));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponseDTO>> findPostsByUserId(@PathVariable Long userId) {
        List<Post> postsByUserId = postService.findPostsByUserId(userId);
        return ResponseEntity.ok(postMapper.toListPostResponseDto(postsByUserId));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> update(@PathVariable Long postId, @Valid @RequestBody UpdatePostDTO updatePostDTO) {
        Post post = postService.update(postId, updatePostDTO);
        return ResponseEntity.ok(postMapper.toPostResponseDto(post));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deleteById(@PathVariable Long postId) {
        postService.deleteById(postId);
        return ResponseEntity.noContent().build();
    }

}
