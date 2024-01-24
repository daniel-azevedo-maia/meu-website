package net.daniel.azevedo.meuwebsite.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @NotBlank
    @Column(nullable = false)
    private String subtitulo;

    @NotBlank
    @Column(nullable = false)
    private String texto;

    @NotBlank
    @Column(nullable = false)
    private String urlImagem;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dataHoraCriacao;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime atualizacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;


}
