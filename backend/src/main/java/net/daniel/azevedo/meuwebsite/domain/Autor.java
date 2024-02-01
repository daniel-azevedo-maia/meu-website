package net.daniel.azevedo.meuwebsite.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "autor")
public class Autor extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "autor")  // Post é a proprietária do relacionamento, pois contém a chave estrangeira de Autor
    private List<Post> posts;

}