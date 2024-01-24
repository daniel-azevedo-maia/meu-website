package net.daniel.azevedo.meuwebsite.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @NotNull
    @Column(nullable = false)
    @OneToMany(mappedBy = "autor")
    private List<Post> posts;


}