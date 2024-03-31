package net.daniel.azevedo.meuwebsite.controller;


import jakarta.validation.Valid;
import net.daniel.azevedo.meuwebsite.dto.usuario.CreateUsuarioDTO;
import net.daniel.azevedo.meuwebsite.dto.usuario.UpdateUsuarioDTO;
import net.daniel.azevedo.meuwebsite.dto.usuario.UsuarioResponseDTO;
import net.daniel.azevedo.meuwebsite.service.UsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        logger.info("Listando todos os usuários");
        List<UsuarioResponseDTO> usuarios = usuarioService.listar();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid CreateUsuarioDTO createUsuarioDTO) {
        logger.info("Requisição para cadastrar novo usuário: {}", createUsuarioDTO.getNome());
        UsuarioResponseDTO usuarioCadastrado = usuarioService.cadastrar(createUsuarioDTO);
        logger.info("Usuário cadastrado com sucesso: {}", usuarioCadastrado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastrado);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long usuarioId) {
        logger.info("Buscando usuário com ID: {}", usuarioId);
        UsuarioResponseDTO usuarioEncontrado = usuarioService.buscarPorId(usuarioId);
        return ResponseEntity.ok(usuarioEncontrado);
    }

    @PutMapping("{usuarioId}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@RequestBody @Valid UpdateUsuarioDTO updateUsuarioDTO,
                                                        @PathVariable Long usuarioId) {
        logger.info("Requisição para atualizar usuário com ID: {}", usuarioId);
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(updateUsuarioDTO, usuarioId);
        logger.info("Usuário com ID: {} atualizado com sucesso", usuarioId);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("{usuarioId}")
    public ResponseEntity<?> removerPorId(@PathVariable Long usuarioId) {
        logger.info("Requisição para remover usuário com ID: {}", usuarioId);
        usuarioService.removerPorId(usuarioId);
        logger.info("Usuário com ID: {} removido com sucesso", usuarioId);
        return ResponseEntity.noContent().build();
    }
}

