package net.daniel.azevedo.meuwebsite.repository;

import net.daniel.azevedo.meuwebsite.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
