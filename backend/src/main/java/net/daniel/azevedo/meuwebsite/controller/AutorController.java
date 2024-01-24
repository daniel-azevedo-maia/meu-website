package net.daniel.azevedo.meuwebsite.controller;

import net.daniel.azevedo.meuwebsite.domain.Autor;
import net.daniel.azevedo.meuwebsite.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ResponseEntity<List<Autor>> listar() {
        List<Autor> autores = autorService.listar();

        if(autores.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(autores);
        }
    }

    @PostMapping
    public ResponseEntity<Autor> cadastrar(@RequestBody Autor autor) {
        Autor autorPersistido = autorService.cadastrar(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorPersistido);

    }

}
