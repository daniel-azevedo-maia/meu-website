package net.daniel.azevedo.meuwebsite.service;

import net.daniel.azevedo.meuwebsite.domain.Autor;

import net.daniel.azevedo.meuwebsite.domain.Post;
import net.daniel.azevedo.meuwebsite.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    private AutorService autorService;

    public PostService(PostRepository postRepository, AutorService autorService) {
        this.postRepository = postRepository;
        this.autorService = autorService;
    }

    public List<Post> listar() {
        return postRepository.findAll();
    }

    public Post cadastrar(Post post) {

        Autor autor = autorService.buscarPorId(post.getAutor().getId());
        post.setAutor(autor);

        return postRepository.save(post);

    }

    public Post buscarPorId(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post não encontrado!"));
    }

    public void removerPorId(Long postId) {

        Post postEncontrado = buscarPorId(postId);
        postRepository.deleteById(postId);

    }

    public Post atualizar(Post post, Long postId) {

        Post postAntigo = buscarPorId(postId);

        postAntigo.setTitulo(post.getTitulo());
        postAntigo.setSubtitulo(post.getSubtitulo());
        postAntigo.setTexto(post.getTexto());
        postAntigo.setCategoria(post.getCategoria());
        postAntigo.setUrlImagem(post.getUrlImagem());
        postAntigo.setAtualizacao(LocalDateTime.now());

        return cadastrar(postAntigo);

    }
}
