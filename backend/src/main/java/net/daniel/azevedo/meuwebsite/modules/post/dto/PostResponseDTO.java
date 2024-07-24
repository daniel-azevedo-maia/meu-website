package net.daniel.azevedo.meuwebsite.modules.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.core.domain.Category;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {

    private Long postId;
    private String title;
    private String subtitle;
    private String text;
    private String imageUrl;
    private LocalDateTime creationDateTime;
    private LocalDateTime updateDateTime;
    private Category category;
    private Long userId;

}
