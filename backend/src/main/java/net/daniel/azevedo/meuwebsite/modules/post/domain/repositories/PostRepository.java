package net.daniel.azevedo.meuwebsite.modules.post.domain.repositories;

import net.daniel.azevedo.meuwebsite.core.domain.Category;
import net.daniel.azevedo.meuwebsite.modules.post.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Post p WHERE p.category = :category")
    List<Post> findByCategory(@Param("category") Category category);

    @Query("SELECT p FROM Post p WHERE " +
            "(:word IS NULL OR p.text LIKE CONCAT('%', :word, '%') OR p.title LIKE CONCAT('%', :word, '%') OR p.subtitle LIKE CONCAT('%', :word, '%')) AND " +
            "(:category IS NULL OR p.category = :category)")
    List<Post> search(@Param("word") String word,
                      @Param("category") Category category);


}
