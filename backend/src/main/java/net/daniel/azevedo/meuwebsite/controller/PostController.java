package net.daniel.azevedo.meuwebsite.controller;

import jakarta.validation.Valid;
import net.daniel.azevedo.meuwebsite.domain.Post;
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

    @PostMapping
    public ResponseEntity<Post> cadastrar(@RequestBody @Valid Post post) {
        Post postPersistido = postService.cadastrar(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(postPersistido);

    }

    @PutMapping("{postId}")
    public ResponseEntity<?> atualizar(@RequestBody @Valid Post post, @PathVariable Long postId) {
        Post postAtualizado = postService.atualizar(post, postId);
        return ResponseEntity.ok(postAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<Post>> listar() {
        List<Post> posts = postService.listar();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long postId) {
        Post postEncontrado = postService.buscarPorId(postId);

        return ResponseEntity.ok(postEncontrado);
    }


    @DeleteMapping("{postId}")
    public ResponseEntity<?> removerPorId(@PathVariable Long postId) {

        postService.removerPorId(postId);
        return ResponseEntity.noContent().build();

    }

}
