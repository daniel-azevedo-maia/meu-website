package com.danielazevedo.website.controller;

import com.danielazevedo.website.exception.EntidadeEmUsoException;
import com.danielazevedo.website.exception.EntidadeNaoEncontradaException;
import com.danielazevedo.website.model.Artigo;
import com.danielazevedo.website.service.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/gerenciar-artigos")
@CrossOrigin(origins = "*")
public class ArtigoController {
    @Autowired
    private ArtigoService artigoService;

    @GetMapping("/listarTodos")
    public List<Artigo> listarTodos() {
        return artigoService.listar();
    }

    @PostMapping("/novo")
    public Artigo novoArtigo(@RequestPart("titulo") String titulo,
                             @RequestPart("subtitulo") String subtitulo,
                             @RequestPart("texto") String texto,
                             @RequestPart(value = "imagem", required = false) MultipartFile imagem) throws IOException {
        Artigo artigo = new Artigo();
        artigo.setTitulo(titulo);
        artigo.setSubtitulo(subtitulo);
        artigo.setTexto(texto);

        if (imagem != null && !imagem.isEmpty()) {
            String nomeArquivo = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();
            String diretorioImagens = "src/main/resources/static/imagens";
            File arquivoDestino = new File(Paths.get(diretorioImagens, nomeArquivo).toString());
            try {
                FileOutputStream fos = new FileOutputStream(arquivoDestino);
                fos.write(imagem.getBytes());
                fos.close();
            } catch (IOException e) {
                throw new IOException("Não foi possível salvar a imagem", e);
            }

            artigo.setCaminhoImagem(Paths.get(diretorioImagens, nomeArquivo).toString());
        }

        return artigoService.cadastrarArtigo(artigo);
    }


    @DeleteMapping("/deletar/{artigoId}")
    public ResponseEntity<?> excluir(@PathVariable Long artigoId) {
        try {
            artigoService.excluir(artigoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
