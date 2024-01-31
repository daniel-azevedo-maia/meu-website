package net.daniel.azevedo.meuwebsite.service;


import net.daniel.azevedo.meuwebsite.domain.Autor;
import net.daniel.azevedo.meuwebsite.dto.AutorDTO;
import net.daniel.azevedo.meuwebsite.dto.CreateAutorDTO;
import net.daniel.azevedo.meuwebsite.dto.UpdateAutorDTO;
import net.daniel.azevedo.meuwebsite.repository.AutorRepository;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<AutorDTO> listar() {

        List<Autor> autores = autorRepository.findAll();
        List<AutorDTO> autoresDTO = new ArrayList<>();

        for (Autor autor : autores) {
            AutorDTO autorDTO = converterAutorParaDTO(autor);
            autoresDTO.add(autorDTO);
        }

        return autoresDTO;
    }

    public AutorDTO cadastrar(CreateAutorDTO createAutorDTO) {
        Autor autor = converterCreateAutorDTOParaAutor(createAutorDTO);
        Autor autorCadastrado = autorRepository.save(autor);
        AutorDTO autorCadastradoDTO = converterAutorParaDTO(autorCadastrado);
        return autorCadastradoDTO;
    }


    public AutorDTO buscarPorId(Long autorId) {
        Autor autorEncontrado = buscarAutor(autorId);
        AutorDTO autorDTO = converterAutorParaDTO(autorEncontrado);
        return autorDTO;
    }

    public void removerPorId(Long autorId) {

        Autor autorEncontrado = buscarAutor(autorId);
        autorRepository.deleteById(autorId);

    }

    public AutorDTO atualizar(UpdateAutorDTO updateAutorDTO, Long autorId) {

        Autor autorExistente = autorRepository.findById(autorId).orElseThrow(() -> new RuntimeException("Autor não encontrado!"));

        autorExistente.setNome(updateAutorDTO.getNome());
        autorExistente.setUsername(updateAutorDTO.getUsername());

        if(updateAutorDTO.getPassword() != null && !updateAutorDTO.getPassword().isEmpty()) {
            autorExistente.setPassword(updateAutorDTO.getPassword());
        }

        Autor autorAtualizado  = autorRepository.save(autorExistente);

        return converterAutorParaDTO(autorAtualizado);

    }

    private Autor converterCreateAutorDTOParaAutor(CreateAutorDTO createAutorDTO) {
        Autor autor = new Autor();

        autor.setNome(createAutorDTO.getNome());
        autor.setUsername(createAutorDTO.getUsername());
        autor.setPassword(createAutorDTO.getPassword());

        return autor;
    }

    private AutorDTO converterAutorParaDTO(Autor autor) {
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setId(autor.getId());
        autorDTO.setNome(autor.getNome());
        return autorDTO;
    }

    private Autor buscarAutor(Long autorId) {
        return autorRepository.findById(autorId).orElseThrow(() -> new RuntimeException("Autor não encontrado!"));
    }

}
