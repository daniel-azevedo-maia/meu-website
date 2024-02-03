package net.daniel.azevedo.meuwebsite.controller;


import jakarta.validation.Valid;
import net.daniel.azevedo.meuwebsite.dto.usuario.CreateUsuarioDTO;
import net.daniel.azevedo.meuwebsite.dto.usuario.UsuarioDTO;
import net.daniel.azevedo.meuwebsite.dto.usuario.UpdateUsuarioDTO;
import net.daniel.azevedo.meuwebsite.dto.usuario.UsuarioResponseDTO;
import net.daniel.azevedo.meuwebsite.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listar();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid CreateUsuarioDTO createUsuarioDTO) {
        UsuarioResponseDTO usuarioCadastrado = usuarioService.cadastrar(createUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastrado);

    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long usuarioId) {
        UsuarioResponseDTO usuarioEncontrado = usuarioService.buscarPorId(usuarioId);

        return ResponseEntity.ok(usuarioEncontrado);
    }

    @PutMapping("{usuarioId}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@RequestBody @Valid UpdateUsuarioDTO updateUsuarioDTO,
                                              @PathVariable Long usuarioId) throws NoSuchFieldException, IllegalAccessException {
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(updateUsuarioDTO, usuarioId);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("{usuarioId}")
    public ResponseEntity<?> removerPorId(@PathVariable Long usuarioId) {

        usuarioService.removerPorId(usuarioId);
        return ResponseEntity.noContent().build();

    }

}
