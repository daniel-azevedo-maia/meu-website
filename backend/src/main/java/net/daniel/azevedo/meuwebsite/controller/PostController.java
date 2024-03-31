package net.daniel.azevedo.meuwebsite.controller;

import jakarta.validation.Valid;
import net.daniel.azevedo.meuwebsite.dto.post.CreatePostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.UpdatePostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.PostResponseDTO;
import net.daniel.azevedo.meuwebsite.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> listar() {
        logger.info("Listando todos os posts");
        List<PostResponseDTO> posts = postService.listar();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> cadastrar(@RequestBody @Valid CreatePostDTO createPostDTO) {
        logger.info("Requisição para cadastrar novo post recebida: {}", createPostDTO.getTitulo());
        PostResponseDTO postCadastrado = postService.cadastrar(createPostDTO);
        logger.info("Post cadastrado com sucesso: {}", postCadastrado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(postCadastrado);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> buscarPorId(@PathVariable Long postId) {
        logger.info("Buscando post com ID: {}", postId);
        PostResponseDTO postEncontrado = postService.buscarPorId(postId);
        return ResponseEntity.ok(postEncontrado);
    }

    @GetMapping("/por-usuario")
    public ResponseEntity<List<PostResponseDTO>> buscarPostsUsuario(@RequestParam Long usuarioId) {
        logger.info("Buscando posts do usuário com ID: {}", usuarioId);
        List<PostResponseDTO> postsEncontrados = postService.buscarPostsPorUsuario(usuarioId);
        return ResponseEntity.ok(postsEncontrados);
    }

    @PutMapping("{postId}")
    public ResponseEntity<?> atualizar(@RequestBody @Valid UpdatePostDTO updatePostDTO, @PathVariable Long postId) {
        logger.info("Requisição para atualizar post com ID: {}", postId);
        PostResponseDTO postAtualizado = postService.atualizar(updatePostDTO, postId);
        logger.info("Post com ID: {} atualizado com sucesso", postId);
        return ResponseEntity.ok(postAtualizado);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> removerPorId(@PathVariable Long postId) {
        logger.info("Requisição para remover post com ID: {}", postId);
        postService.removerPorId(postId);
        logger.info("Post com ID: {} removido com sucesso", postId);
        return ResponseEntity.noContent().build();
    }
}



