package net.daniel.azevedo.meuwebsite.modules.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListPostsDTO {

    private Long postId;

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotNull
    private Category category;

    @NotNull
    private Long userId;

}