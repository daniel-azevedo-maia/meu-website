package net.daniel.azevedo.meuwebsite.modules.post.domain.repositories;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {



}
