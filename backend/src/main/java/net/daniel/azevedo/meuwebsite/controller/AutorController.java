package net.daniel.azevedo.meuwebsite.controller;

import jakarta.validation.Valid;
import net.daniel.azevedo.meuwebsite.domain.Autor;
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

    @PostMapping
    public ResponseEntity<Autor> cadastrar(@RequestBody @Valid Autor autor) {
        Autor autorPersistido = autorService.cadastrar(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorPersistido);

    }

    @PutMapping("{autorId}")
    public ResponseEntity<?> atualizar(@RequestBody @Valid Autor autor, @PathVariable Long autorId) {
        Autor autorAtualizado = autorService.atualizar(autor, autorId);
        return ResponseEntity.ok(autorAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listar() {
        List<Autor> autores = autorService.listar();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/{autorId}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long autorId) {
        Autor autorEncontrado = autorService.buscarPorId(autorId);

        return ResponseEntity.ok(autorEncontrado);
    }


    @DeleteMapping("{autorId}")
    public ResponseEntity<?> removerPorId(@PathVariable Long autorId) {

        autorService.removerPorId(autorId);
        return ResponseEntity.noContent().build();

    }

}
