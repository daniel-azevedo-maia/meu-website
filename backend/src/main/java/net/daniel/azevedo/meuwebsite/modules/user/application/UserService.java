package net.daniel.azevedo.meuwebsite.modules.user.application;

import lombok.RequiredArgsConstructor;
import net.daniel.azevedo.meuwebsite.modules.post.domain.repositories.PostRepository;
import net.daniel.azevedo.meuwebsite.modules.user.dto.UpdateUserDTO;
import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;
import net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions.UserNotFoundException;
import net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions.UsernameUniqueViolationException;
import net.daniel.azevedo.meuwebsite.modules.user.domain.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();

    }

    @Transactional
    public User create(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(user.getUsername());
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
    }

    @Transactional
    public void deleteById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public User update(Long userId, UpdateUserDTO updateUserDTO) {
        User user = findById(userId);

        Optional.ofNullable(updateUserDTO.getPassword()).ifPresent(user::setPassword);
        Optional.ofNullable(updateUserDTO.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(updateUserDTO.getAddress()).ifPresent(user::setAddress);
        Optional.ofNullable(updateUserDTO.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(updateUserDTO.getLastName()).ifPresent(user::setLastName);

        return userRepository.save(user);
    }

}
