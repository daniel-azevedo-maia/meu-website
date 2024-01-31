package net.daniel.azevedo.meuwebsite.service;

import net.daniel.azevedo.meuwebsite.domain.Autor;

import net.daniel.azevedo.meuwebsite.domain.Post;
import net.daniel.azevedo.meuwebsite.dto.AutorDTO;
import net.daniel.azevedo.meuwebsite.dto.PostDTO;
import net.daniel.azevedo.meuwebsite.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    private AutorService autorService;

    public PostService(PostRepository postRepository, AutorService autorService) {
        this.postRepository = postRepository;
        this.autorService = autorService;
    }

    public List<PostDTO> listar() {

        List<Post> posts = postRepository.findAll();
        List<PostDTO> postsDTO = new ArrayList<>();

        for(Post post : posts) {
            AutorDTO autorDto = converterParaAutorDTO(post.getAutor());
            PostDTO postDTO = converterParaPostDTO(post);
            postDTO.setAutor(autorDto);
            postsDTO.add(postDTO);
        }

        return postsDTO;
    }

    public Post cadastrar(Post post) {

        Autor autor = autorService.buscarPorId(post.getAutor().getId());
        AutorDTO autorDTO = converterParaAutorDTO(autor);

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


    private PostDTO converterParaPostDTO(Post post) {

        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setTitulo(post.getTitulo());
        postDTO.setSubtitulo(post.getSubtitulo());
        postDTO.setTexto(post.getTexto());
        postDTO.setUrlImagem(post.getUrlImagem());
        postDTO.setDataHoraCriacao(post.getDataHoraCriacao());
        postDTO.setAtualizacao(post.getAtualizacao());
        postDTO.setCategoria(post.getCategoria());

        return postDTO;
    }

    private AutorDTO converterParaAutorDTO(Autor autor) {
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setId(autor.getId());
        autorDTO.setNome(autor.getNome());

        return autorDTO;
    }

}
