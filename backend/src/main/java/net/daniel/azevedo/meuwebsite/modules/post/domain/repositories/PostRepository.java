package net.daniel.azevedo.meuwebsite.modules.post.domain.repositories;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM posts p WHERE p.user_id = :userId", nativeQuery = true)
    List<Post> findAllByUserId(@Param("userId") Long userId);
}
