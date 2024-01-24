package net.daniel.azevedo.meuwebsite.service;

import net.daniel.azevedo.meuwebsite.domain.Autor;
import net.daniel.azevedo.meuwebsite.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listar() {
        return autorRepository.findAll();
    }

    public Autor cadastrar(Autor autor) {

        return autorRepository.save(autor);

    }
}
