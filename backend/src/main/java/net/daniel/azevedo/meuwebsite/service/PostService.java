package net.daniel.azevedo.meuwebsite.service;

import jakarta.persistence.EntityNotFoundException;
import net.daniel.azevedo.meuwebsite.domain.Post;
import net.daniel.azevedo.meuwebsite.domain.Usuario;
import net.daniel.azevedo.meuwebsite.dto.post.CreatePostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.UpdatePostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.PostResponseDTO;
import net.daniel.azevedo.meuwebsite.exception.post.PostNotFoundException;
import net.daniel.azevedo.meuwebsite.exception.usuario.UsuarioNotFoundException;
import net.daniel.azevedo.meuwebsite.repository.PostRepository;
import net.daniel.azevedo.meuwebsite.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private UsuarioRepository usuarioRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    public PostService(PostRepository postRepository, UsuarioRepository usuarioRepository) {
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PostResponseDTO> listar() {
        logger.info("Listando todos os posts");
        List<Post> posts = postRepository.findAll();
        List<PostResponseDTO> postsResponseDTO = posts.stream()
                .map(this::converterParaPostResponseDTO)
                .collect(Collectors.toList());
        return postsResponseDTO;
    }

    @Transactional
    public PostResponseDTO cadastrar(CreatePostDTO createPostDTO) {
        logger.info("Cadastrando novo post: {}", createPostDTO.getTitulo());
        Post post = converterParaPost(createPostDTO);
        post.setUsuario(usuarioRepository.findById(createPostDTO.getUsuarioId())
                .orElseThrow(() -> new UsuarioNotFoundException(createPostDTO.getUsuarioId())));
        post.setDataHoraCriacao(LocalDateTime.now());
        Post postSalvo = postRepository.save(post);
        return converterParaPostResponseDTO(postSalvo);
    }

    public PostResponseDTO buscarPorId(Long postId) {
        logger.info("Buscando post com ID: {}", postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        return converterParaPostResponseDTO(post);
    }

    public List<PostResponseDTO> buscarPostsPorUsuario(Long usuarioId) {
        logger.info("Buscando posts do usuário com ID: {}", usuarioId);
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioId));
        List<PostResponseDTO> posts = usuario.getPosts().stream()
                .map(this::converterParaPostResponseDTO)
                .collect(Collectors.toList());
        return posts;
    }

    @Transactional
    public PostResponseDTO atualizar(UpdatePostDTO updatePostDTO, Long postId) {
        logger.info("Atualizando post com ID: {}", postId);
        Post postParaAtualizar = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        BeanUtils.copyProperties(updatePostDTO, postParaAtualizar, "id");
        Post postAtualizado = postRepository.save(postParaAtualizar);
        return converterParaPostResponseDTO(postAtualizado);
    }

    @Transactional
    public void removerPorId(Long postId) {
        logger.info("Removendo post com ID: {}", postId);
        postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        try {
            postRepository.deleteById(postId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Erro ao remover o post com ID: {}", postId, e);
            throw new PostNotFoundException(postId);
        }
    }

    // Métodos para manipulação de DTOs -------------------------------------

    private PostResponseDTO converterParaPostResponseDTO(Post post) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setId(post.getId());
        postResponseDTO.setTitulo(post.getTitulo());
        return postResponseDTO;
    }

    private Post converterParaPost(CreatePostDTO createPostDTO) {
        Post post = new Post();
        post.setTitulo(createPostDTO.getTitulo());
        post.setSubtitulo(createPostDTO.getSubtitulo());
        post.setTexto(createPostDTO.getTexto());
        post.setUrlImagem(createPostDTO.getUrlImagem());
        post.setCategoria(createPostDTO.getCategoria());
        return post;
    }

}