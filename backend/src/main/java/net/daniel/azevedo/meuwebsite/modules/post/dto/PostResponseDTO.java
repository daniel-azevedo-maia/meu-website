package net.daniel.azevedo.meuwebsite.modules.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.core.domain.Categoria;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {

    private UUID postId;
    private String titulo;
    private String subtitulo;
    private String texto;
    private String urlImagem;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime atualizacao;
    private Categoria categoria;
    private UUID userId;

}