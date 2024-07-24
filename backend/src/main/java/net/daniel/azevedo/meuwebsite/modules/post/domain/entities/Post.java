package net.daniel.azevedo.meuwebsite.modules.post.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.core.domain.Categoria;
import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_id")
    private UUID postId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank
    @Column(nullable = false, length = 350)
    private String subtitulo;

    @NotBlank
    @Column(nullable = false, length = 10_000)
    private String texto;

    @NotBlank
    @Column(nullable = false)
    private String urlImagem;

    @Column(name = "data_hora_criacao", nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao;

    @Column(nullable = false)
    private LocalDateTime atualizacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    // Método que será chamado antes de atualizar a entidade
    @PreUpdate
    protected void upUpdate() {
        this.atualizacao = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", usuario=" + user +
                ", titulo='" + titulo + '\'' +
                ", subtitulo='" + subtitulo + '\'' +
                ", texto='" + texto + '\'' +
                ", urlImagem='" + urlImagem + '\'' +
                ", dataHoraCriacao=" + dataHoraCriacao +
                ", atualizacao=" + atualizacao +
                ", categoria=" + categoria +
                '}';
    }
}
