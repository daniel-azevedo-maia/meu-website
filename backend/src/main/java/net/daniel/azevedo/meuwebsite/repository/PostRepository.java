package net.daniel.azevedo.meuwebsite.repository;

import net.daniel.azevedo.meuwebsite.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
