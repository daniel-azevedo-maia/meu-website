package net.daniel.azevedo.meuwebsite.modules.post.dto;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Category;

@Getter @Setter
public class PostSearch {

    @Size(max = 255, message = "A palavra deve ter no máximo 255 caracteres")
    private String word;

    private Category category;

}
