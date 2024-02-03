package net.daniel.azevedo.meuwebsite.service;

import net.daniel.azevedo.meuwebsite.domain.Post;
import net.daniel.azevedo.meuwebsite.domain.Usuario;
import net.daniel.azevedo.meuwebsite.dto.post.CreatePostDTO;
import net.daniel.azevedo.meuwebsite.dto.post.UpdatePostDTO;
import net.daniel.azevedo.meuwebsite.dto.usuario.PostResponseDTO;
import net.daniel.azevedo.meuwebsite.repository.PostRepository;
import net.daniel.azevedo.meuwebsite.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private UsuarioRepository usuarioRepository;

    public PostService(PostRepository postRepository, UsuarioRepository usuarioRepository) {
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PostResponseDTO> listar() {

        List<Post> posts = postRepository.findAll();
        List<PostResponseDTO> postsResponseDTO = posts.stream().map(post
                -> converterParaPostResponseDTO(post)).collect(Collectors.toList());

        return postsResponseDTO;
    }

    public PostResponseDTO cadastrar(CreatePostDTO createPostDTO) {

        Post post = converterParaPost(createPostDTO);
        Long usuarioId = createPostDTO.getUsuarioId();

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(()
                -> new RuntimeException("Usuário não encontrado!"));

        post.setUsuario(usuario);
        post.setDataHoraCriacao(LocalDateTime.now());

        Post postSalvo = postRepository.save(post);

        return converterParaPostResponseDTO(postSalvo);

    }

    public PostResponseDTO buscarPorId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado!"));
        return converterParaPostResponseDTO(post);
    }

    public PostResponseDTO atualizar(UpdatePostDTO updatePostDTO, Long postId) {

        Post postParaAtualizar = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado!"));

        BeanUtils.copyProperties(updatePostDTO, postParaAtualizar, "id");

        Post postAtualizado = postRepository.save(postParaAtualizar);

        return converterParaPostResponseDTO(postAtualizado);

    }

    public void removerPorId(Long postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado!"));
        postRepository.deleteById(postId);
    }

    // Métodos para manipulação de DTOs -------------------------------------

    // Usado
    private PostResponseDTO converterParaPostResponseDTO(Post post) {

        PostResponseDTO postResponseDTO = new PostResponseDTO();

        postResponseDTO.setId(post.getId());
        postResponseDTO.setTitulo(post.getTitulo());
        return postResponseDTO;

    }

    // Usado
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
