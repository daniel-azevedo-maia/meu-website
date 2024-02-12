package net.daniel.azevedo.meuwebsite.controller;

import jakarta.validation.Valid;
import net.daniel.azevedo.meuwebsite.dto.post.CreatePostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.UpdatePostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.PostResponseDTO;
import net.daniel.azevedo.meuwebsite.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> listar() {
        List<PostResponseDTO> posts = postService.listar();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> cadastrar(@RequestBody @Valid CreatePostDTO createPostDTO) {
        PostResponseDTO postCadastrado = postService.cadastrar(createPostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postCadastrado);

    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> buscarPorId(@PathVariable Long postId) {
        PostResponseDTO postEncontrado = postService.buscarPorId(postId);
        return ResponseEntity.ok(postEncontrado);
    }

    @GetMapping("/por-usuario")
    public ResponseEntity<List<PostResponseDTO>> buscarPostsUsuario(@RequestParam Long usuarioId) {
        List<PostResponseDTO> postsEncontrados = postService.buscarPostsPorUsuario(usuarioId);
        return ResponseEntity.ok(postsEncontrados);
    }

    @PutMapping("{postId}")
    public ResponseEntity<?> atualizar(@RequestBody @Valid UpdatePostDTO updatePostDTO, @PathVariable Long postId) {
        PostResponseDTO postAtualizado = postService.atualizar(updatePostDTO, postId);
        return ResponseEntity.ok(postAtualizado);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> removerPorId(@PathVariable Long postId) {
        postService.removerPorId(postId);
        return ResponseEntity.noContent().build();
    }

}
