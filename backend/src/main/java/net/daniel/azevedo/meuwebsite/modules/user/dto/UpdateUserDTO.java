package net.daniel.azevedo.meuwebsite.modules.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.core.domain.Address;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UpdateUserDTO {

    @Size(min = 6, max = 200)
    private String password;

    @Email(message = "Email format is invalid")
    private String email;

    @Size(min = 1, max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(min = 1, max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    private Address address;

}
