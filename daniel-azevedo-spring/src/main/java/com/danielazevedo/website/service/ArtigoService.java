package com.danielazevedo.website.service;

import com.danielazevedo.website.model.Artigo;
import com.danielazevedo.website.repository.ArtigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository artigoRepository;

    public List<Artigo> listar() {
        return artigoRepository.findAll();
    }

    public Artigo cadastrarArtigo(Artigo artigo) {

        return artigoRepository.save(artigo);
    }

    public void excluir(Long artigoId) {
        artigoRepository.deleteById(artigoId);
    }

}
