package net.daniel.azevedo.meuwebsite.modules.user.api;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import net.daniel.azevedo.meuwebsite.modules.user.dto.CreateUserDTO;
import net.daniel.azevedo.meuwebsite.modules.user.dto.UpdateUserDTO;
import net.daniel.azevedo.meuwebsite.modules.user.dto.UserDetailDTO;
import net.daniel.azevedo.meuwebsite.modules.user.dto.UserResponseDTO;
import net.daniel.azevedo.meuwebsite.modules.user.mapper.UserMapper;
import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;
import net.daniel.azevedo.meuwebsite.modules.user.application.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(userMapper.toListUserResponseDto(users));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid CreateUserDTO createUserDTO) {
        User user = userService.create(userMapper.toEntity(createUserDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toUserResponseDto(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailDTO> findById(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(userMapper.toUserDetailDto(user));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long userId, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        User user = userService.update(userId, updateUserDTO);
        return ResponseEntity.ok(userMapper.toUserResponseDto(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}

