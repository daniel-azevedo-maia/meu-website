package net.daniel.azevedo.meuwebsite.modules.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.core.domain.Categoria;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDTO {

    @NotBlank
    @Size(max = 150)
    private String titulo;

    @NotBlank
    @Size(max = 350)
    private String subtitulo;

    @NotBlank
    @Size(max = 10_000)
    private String texto;

    @NotBlank
    private String urlImagem;

    @NotNull
    private Categoria categoria;

    @NotNull
    private UUID userId;

}