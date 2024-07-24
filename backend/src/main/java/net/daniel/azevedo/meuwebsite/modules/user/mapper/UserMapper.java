package net.daniel.azevedo.meuwebsite.modules.user.mapper;

import lombok.AllArgsConstructor;
import net.daniel.azevedo.meuwebsite.modules.user.dto.CreateUserDTO;
import net.daniel.azevedo.meuwebsite.modules.user.dto.UserResponseDTO;
import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserResponseDTO toUserResponseDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public User toEntity(CreateUserDTO createUserDTO) {
        return modelMapper.map(createUserDTO, User.class);
    }

    public List<UserResponseDTO> toListUserResponseDto (List<User> users) {
        return users.stream().map(user -> toUserResponseDto(user)).collect(Collectors.toList());
    }

}
