package net.daniel.azevedo.meuwebsite.modules.user.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.daniel.azevedo.meuwebsite.core.domain.Address;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @NotBlank
    @Size(min = 6, max = 200)
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @NotBlank
    @Email(message = "Email format is invalid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Past
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Embedded
    private Address address;

    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", address=" + address +
                ", registrationDate=" + registrationDate +
                '}';
    }

    // Method that will be called before persisting the entity
    @PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDateTime.now();
    }
}
