package net.daniel.azevedo.meuwebsite.modules.post.mapper;

import lombok.AllArgsConstructor;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Post;
import net.daniel.azevedo.meuwebsite.modules.post.dto.ListPostsDTO;
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

    public List<ListPostsDTO> toListPostsDto(List<Post> posts) {
        return posts.stream()
                .map(post -> modelMapper.map(post, ListPostsDTO.class))
                .collect(Collectors.toList());
    }

}
