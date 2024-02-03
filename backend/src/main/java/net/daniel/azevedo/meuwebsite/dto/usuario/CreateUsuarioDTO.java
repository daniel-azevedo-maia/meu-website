package net.daniel.azevedo.meuwebsite.dto.usuario;

import lombok.Getter;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.domain.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateUsuarioDTO {

    private String nome;
    private String username;
    private String password;
    private List<Post> posts = new ArrayList<>();

}
