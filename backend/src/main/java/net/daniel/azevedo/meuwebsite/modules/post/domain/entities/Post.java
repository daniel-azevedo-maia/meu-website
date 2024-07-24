package net.daniel.azevedo.meuwebsite.modules.post.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.core.domain.Category;
import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String title;

    @NotBlank
    @Column(nullable = false, length = 350)
    private String subtitle;

    @NotBlank
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @NotBlank
    @Column(nullable = false)
    private String imageUrl;

    @Column(name = "creation_date_time", nullable = false, updatable = false)
    private LocalDateTime creationDateTime;

    @Column(name = "update_date_time", nullable = false)
    private LocalDateTime updateDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @PrePersist
    protected void onCreate() {
        this.creationDateTime = LocalDateTime.now();
        this.updateDateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDateTime = LocalDateTime.now();
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
                ", user=" + user +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", text='" + text + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", creationDateTime=" + creationDateTime +
                ", updateDateTime=" + updateDateTime +
                ", category=" + category +
                '}';
    }
}
