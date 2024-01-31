package net.daniel.azevedo.meuwebsite.controller;

import jakarta.validation.Valid;
import net.daniel.azevedo.meuwebsite.domain.Post;
import net.daniel.azevedo.meuwebsite.dto.autor.CreateAutorDTO;
import net.daniel.azevedo.meuwebsite.dto.post.CreatePostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.PostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.UpdatePostDTO;
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
    public ResponseEntity<List<PostDTO>> listar() {
        List<PostDTO> posts = postService.listar();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<PostDTO> cadastrar(@RequestBody @Valid CreatePostDTO createPostDTO) {
        PostDTO postCriado = postService.cadastrar(createPostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(postCriado);

    }

    @PutMapping("{postId}")
    public ResponseEntity<?> atualizar(@RequestBody @Valid UpdatePostDTO updatePostDTO, @PathVariable Long postId) {
        PostDTO postAtualizado = postService.atualizar(updatePostDTO, postId);
        return ResponseEntity.ok(postAtualizado);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> buscarPorId(@PathVariable Long postId) {
        PostDTO postEncontrado = postService.buscarPorId(postId);
        return ResponseEntity.ok(postEncontrado);
    }


    @DeleteMapping("{postId}")
    public ResponseEntity<?> removerPorId(@PathVariable Long postId) {
        postService.removerPorId(postId);
        return ResponseEntity.noContent().build();
    }

}
