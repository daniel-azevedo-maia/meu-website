package net.daniel.azevedo.meuwebsite.dto.autor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAutorDTO {
    private String nome;
    private String username;
    private String password;
}