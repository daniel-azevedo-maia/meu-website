package net.daniel.azevedo.meuwebsite.modules.post.mapper;

import lombok.AllArgsConstructor;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Post;
import net.daniel.azevedo.meuwebsite.modules.post.dto.PostSummaryDTO; // Alterado para PostSummaryDTO
import net.daniel.azevedo.meuwebsite.modules.post.dto.PostResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostMapper {

    private final ModelMapper modelMapper;

    public PostResponseDTO toPostResponseDto(Post post) {
        return modelMapper.map(post, PostResponseDTO.class);
    }

    public List<PostResponseDTO> toListPostResponseDto(List<Post> posts) {
        return posts.stream().map(this::toPostResponseDto).collect(Collectors.toList());
    }

    // Atualizado para usar PostSummaryDTO
    public List<PostSummaryDTO> toPostSummaryDtoList(List<Post> posts) {
        return posts.stream()
                .map(this::toPostSummaryDto) // Usa o método customizado
                .collect(Collectors.toList());
    }

    // Método customizado para mapear um único Post para PostSummaryDTO
    private PostSummaryDTO toPostSummaryDto(Post post) {
        PostSummaryDTO dto = modelMapper.map(post, PostSummaryDTO.class);
        dto.setTextPreview(generateTextPreview(post.getText())); // Preenche o preview
        return dto;
    }

    // Método auxiliar para gerar uma prévia do texto
    private String generateTextPreview(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        int previewLength = Math.min(text.length(), 255); // Limitar a 255 caracteres ou menos
        return text.substring(0, previewLength);
    }
}
