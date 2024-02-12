package net.daniel.azevedo.meuwebsite.service;


import jakarta.transaction.Transactional;
import net.daniel.azevedo.meuwebsite.domain.Usuario;
import net.daniel.azevedo.meuwebsite.dto.usuario.UsuarioDTO;
import net.daniel.azevedo.meuwebsite.dto.usuario.CreateUsuarioDTO;
import net.daniel.azevedo.meuwebsite.dto.usuario.UpdateUsuarioDTO;
import net.daniel.azevedo.meuwebsite.dto.usuario.UsuarioResponseDTO;
import net.daniel.azevedo.meuwebsite.exception.usuario.UsuarioTemPostsException;
import net.daniel.azevedo.meuwebsite.exception.usuario.UsuarioNotFoundException;
import net.daniel.azevedo.meuwebsite.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> listar() {

        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> usuariosResponseDTO = usuarios.stream().map(usuario
                -> converterParaUsuarioResponseDTO(usuario)).collect(Collectors.toList());

        return usuariosResponseDTO;

    }

    @Transactional
    public UsuarioResponseDTO cadastrar(CreateUsuarioDTO createUsuarioDTO) {

        Usuario usuario = converterParaUsuario(createUsuarioDTO);
        usuario.setDataCadastro(LocalDateTime.now());
        Usuario usuarioCadastrado = usuarioRepository.save(usuario);
        UsuarioResponseDTO usuarioResponseDTO = converterParaUsuarioResponseDTO(usuarioCadastrado);

        return usuarioResponseDTO;

    }

    public UsuarioResponseDTO buscarPorId(Long usuarioId) {

        Usuario usuarioEncontrado = executarBuscaUsuario(usuarioId);
        UsuarioResponseDTO usuarioResponseDTO = converterParaUsuarioResponseDTO(usuarioEncontrado);

        return usuarioResponseDTO;

    }

    @Transactional
    public void removerPorId(Long usuarioId) {
        Usuario usuarioEncontrado = executarBuscaUsuario(usuarioId);

        if (!usuarioEncontrado.getPosts().isEmpty()) {
            throw new UsuarioTemPostsException();
        }

        try {
            usuarioRepository.deleteById(usuarioId);
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNotFoundException(usuarioId);
        }
    }


    @Transactional
    public UsuarioResponseDTO atualizar(UpdateUsuarioDTO updateUsuarioDTO, Long usuarioId) {

        Usuario usuarioParaAtualizar = usuarioRepository.findById(usuarioId).orElseThrow(()
                -> new UsuarioNotFoundException(usuarioId));

        BeanUtils.copyProperties(updateUsuarioDTO, usuarioParaAtualizar, "id");
        Usuario usuarioAtualizado = usuarioRepository.save(usuarioParaAtualizar);

        return converterParaUsuarioResponseDTO(usuarioAtualizado);

    }

    // Métodos para manipulação de DTOs -------------------------------------

    private Usuario converterParaUsuario(CreateUsuarioDTO createUsuarioDTO) {

        Usuario usuario = new Usuario();

        usuario.setNome(createUsuarioDTO.getNome());
        usuario.setUsername(createUsuarioDTO.getUsername());
        usuario.setPassword(createUsuarioDTO.getPassword());
        usuario.setPosts(new ArrayList<>());

        return usuario;

    }

    private UsuarioResponseDTO converterParaUsuarioResponseDTO(Usuario usuario) {

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setId(usuario.getId());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setUsername(usuario.getUsername());

        return usuarioResponseDTO;

    }

    private Usuario executarBuscaUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(()
                -> new UsuarioNotFoundException(usuarioId));
    }


}
