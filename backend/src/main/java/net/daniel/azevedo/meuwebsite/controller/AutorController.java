package net.daniel.azevedo.meuwebsite.controller;

import jakarta.validation.Valid;
import net.daniel.azevedo.meuwebsite.dto.autor.AutorDTO;
import net.daniel.azevedo.meuwebsite.dto.autor.CreateAutorDTO;
import net.daniel.azevedo.meuwebsite.dto.autor.UpdateAutorDTO;
import net.daniel.azevedo.meuwebsite.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> listar() {
        List<AutorDTO> autoresDTO = autorService.listar();
        return ResponseEntity.ok(autoresDTO);
    }

    @PostMapping
    public ResponseEntity<AutorDTO> cadastrar(@RequestBody @Valid CreateAutorDTO createAutorDTO) {
        AutorDTO autorDTO = autorService.cadastrar(createAutorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorDTO);

    }

    @PutMapping("{autorId}")
    public ResponseEntity<AutorDTO> atualizar(@RequestBody @Valid UpdateAutorDTO updateAutorDTO,
                                              @PathVariable Long autorId) {
        AutorDTO autorAtualizado = autorService.atualizar(updateAutorDTO, autorId);
        return ResponseEntity.ok(autorAtualizado);
    }

    @GetMapping("/{autorId}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long autorId) {
        AutorDTO autorEncontrado = autorService.buscarPorIdDTO(autorId);

        return ResponseEntity.ok(autorEncontrado);
    }


    @DeleteMapping("{autorId}")
    public ResponseEntity<?> removerPorId(@PathVariable Long autorId) {

        autorService.removerPorId(autorId);
        return ResponseEntity.noContent().build();

    }


}
