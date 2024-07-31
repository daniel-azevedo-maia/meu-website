package net.daniel.azevedo.meuwebsite.modules.user.domain.repositories;

import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
