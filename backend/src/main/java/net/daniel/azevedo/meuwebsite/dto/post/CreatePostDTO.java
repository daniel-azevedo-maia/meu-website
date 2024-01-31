package net.daniel.azevedo.meuwebsite.dto.post;

import lombok.Getter;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.domain.Categoria;

@Getter
@Setter
public class CreatePostDTO {
    private Long autorId;
    private String titulo;
    private String subtitulo;
    private String texto;
    private String urlImagem;
    private Categoria categoria;
}
