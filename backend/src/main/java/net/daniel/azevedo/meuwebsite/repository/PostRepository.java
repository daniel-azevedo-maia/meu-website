package net.daniel.azevedo.meuwebsite.repository;

import net.daniel.azevedo.meuwebsite.domain.Post;
import net.daniel.azevedo.meuwebsite.dto.usuario.PostResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



}
