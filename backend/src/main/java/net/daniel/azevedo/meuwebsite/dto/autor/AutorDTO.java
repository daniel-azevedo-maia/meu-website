package net.daniel.azevedo.meuwebsite.dto.autor;

import lombok.Getter;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.domain.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AutorDTO {

    private Long id;
    private String nome;
    private List<Post> posts = new ArrayList<Post>();

}
