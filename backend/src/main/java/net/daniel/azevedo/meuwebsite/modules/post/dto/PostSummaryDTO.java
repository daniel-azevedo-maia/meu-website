package net.daniel.azevedo.meuwebsite.modules.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Category;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostSummaryDTO {

    private Long postId;

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotBlank
    @Size(max = 350)
    private String subtitle;

    @NotBlank
    @Size(max = 255) // As primeiras linhas do texto do post
    private String textPreview;

    @NotNull
    private Category category;

    @NotNull
    private Long userId;

    private LocalDateTime creationDateTime; // Data de criação para ordenar por mais recentes

}