package net.daniel.azevedo.meuwebsite.service;

import jakarta.transaction.Transactional;
import net.daniel.azevedo.meuwebsite.domain.Autor;

import net.daniel.azevedo.meuwebsite.domain.Post;
import net.daniel.azevedo.meuwebsite.dto.autor.AutorDTO;
import net.daniel.azevedo.meuwebsite.dto.post.CreatePostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.PostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.UpdatePostDTO;
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
            PostDTO postDTO = converterParaPostDTO(post);
            postsDTO.add(postDTO);
        }

        return postsDTO;
    }

    public PostDTO cadastrar(CreatePostDTO createPostDTO) {

        Post post = converterCreatePostDTOParaPost(createPostDTO);

        Autor autor = autorService.buscarPorId(createPostDTO.getAutorId());
        post.setAutor(autor);

        // Ao cadastrar um novo Post e associá-lo a um Autor,
        // apenas definir o Autor no Post e salvar o Post é suficiente

        Post postSalvo = postRepository.save(post);

        return converterParaPostDTO(postSalvo);

    }

    public PostDTO buscarPorId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado!"));
        return converterParaPostDTO(post);
    }

    public void removerPorId(Long postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado!"));
        postRepository.deleteById(postId);
    }

    public PostDTO atualizar(UpdatePostDTO updatePostDTO, Long postId) {
        Post postExistente = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado!"));

        postExistente.setTitulo(updatePostDTO.getTitulo());
        postExistente.setSubtitulo(updatePostDTO.getSubtitulo());
        postExistente.setTexto(updatePostDTO.getTexto());
        postExistente.setCategoria(updatePostDTO.getCategoria());
        postExistente.setUrlImagem(updatePostDTO.getUrlImagem());
        postExistente.setAtualizacao(LocalDateTime.now());

        Post postAtualizado = postRepository.save(postExistente);
        return converterParaPostDTO(postAtualizado);
    }

    private Post converterCreatePostDTOParaPost(CreatePostDTO createPostDTO) {
        Post post = new Post();
        post.setTitulo(createPostDTO.getTitulo());
        post.setSubtitulo(createPostDTO.getSubtitulo());
        post.setTexto(createPostDTO.getTexto());
        post.setUrlImagem(createPostDTO.getUrlImagem());
        post.setCategoria(createPostDTO.getCategoria());

        return post;
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
        postDTO.setAutorId(post.getAutor().getId());

        return postDTO;
    }

    private AutorDTO converterParaAutorDTO(Autor autor) {
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setId(autor.getId());
        autorDTO.setNome(autor.getNome());

        return autorDTO;
    }

}
