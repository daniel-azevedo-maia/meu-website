package net.daniel.azevedo.meuwebsite.modules.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.daniel.azevedo.meuwebsite.core.domain.Address;
import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;
import net.daniel.azevedo.meuwebsite.modules.user.dto.CreateUserDTO;
import net.daniel.azevedo.meuwebsite.modules.user.dto.UpdateUserDTO;
import net.daniel.azevedo.meuwebsite.modules.user.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    // Verifica que não é possível criar um usuário com nome de usuário ou e-mail duplicado
    public void testarNaoPodeCriarUsuarioComNomeDeUsuarioOuEmailDuplicado() throws Exception {
        CreateUserDTO userDTO = new CreateUserDTO("username1", "password", "FirstName", "LastName", "email@example.com", LocalDate.of(1990, 1, 1), new Address("Street", "123", "00000-000", "City", "State", "Country"));
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))) // Converte o objeto em JSON string
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))) // Converte o objeto em JSON string
                .andExpect(status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(org.hamcrest.Matchers.containsString("User already exists!"))); // Verifica se a mensagem de erro contém "User already exists!"
    }

    @Test
    // Verifica a criação bem-sucedida de um usuário
    public void testarCriacaoDeUsuarioComSucesso() throws Exception {
        CreateUserDTO userDTO = new CreateUserDTO("username2", "password", "Pedro", "Sauro", "pedro@example.com", LocalDate.of(1990, 1, 1), new Address("Street", "123", "00000-000", "City", "State", "Country"));
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))) // Converte o objeto em JSON string
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Pedro")) // Verifica se o campo firstName no JSON da resposta é "Pedro"
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Sauro")); // Verifica se o campo lastName no JSON da resposta é "Sauro"
    }

    @Test
    // Verifica a atualização bem-sucedida de um usuário
    public void testarAtualizacaoDeUsuarioComSucesso() throws Exception {
        CreateUserDTO userDTO = new CreateUserDTO("username3", "password", "FirstName", "LastName", "email3@example.com", LocalDate.of(1990, 1, 1), new Address("Street", "123", "00000-000", "City", "State", "Country"));
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setBirthDate(userDTO.getBirthDate());
        user.setAddress(userDTO.getAddress());
        userRepository.save(user);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO(null, "newemail@example.com", "NewFirstName", null, null);
        mockMvc.perform(patch("/api/v1/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateUserDTO))) // Converte o objeto em JSON string
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("NewFirstName")) // Verifica se o campo firstName no JSON da resposta é "NewFirstName"
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username3")); // Verifica campos presentes no UserResponseDTO
    }

    @Test
    // Verifica a recuperação de um usuário pelo ID
    public void testarBuscaDeUsuarioPorId() throws Exception {
        CreateUserDTO userDTO = new CreateUserDTO("username4", "password", "Daniel", "Maya", "daniel@example.com", LocalDate.of(1991, 4, 18), new Address("Minha Nova Rua", "12345", "01000-000", "João Pessoa", "PB", "Brasil"));
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setBirthDate(userDTO.getBirthDate());
        user.setAddress(userDTO.getAddress());
        userRepository.save(user);

        mockMvc.perform(get("/api/v1/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("username4")) // Verifica se o campo username no JSON da resposta é "username4"
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Daniel")); // Verifica se o campo firstName no JSON da resposta é "Daniel"
    }

    @Test
    // Verifica a não deleção de um usuário que não existe
    public void testarNaoPodeDeletarUsuarioNaoExistente() throws Exception {
        mockMvc.perform(delete("/api/v1/users/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    // Verifica a deleção bem-sucedida de um usuário
    public void testarDelecaoDeUsuarioComSucesso() throws Exception {
        CreateUserDTO userDTO = new CreateUserDTO("username5", "password", "FirstName", "LastName", "email5@example.com", LocalDate.of(1990, 1, 1), new Address("Street", "123", "00000-000", "City", "State", "Country"));
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setBirthDate(userDTO.getBirthDate());
        user.setAddress(userDTO.getAddress());
        userRepository.save(user);

        mockMvc.perform(delete("/api/v1/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/api/v1/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
