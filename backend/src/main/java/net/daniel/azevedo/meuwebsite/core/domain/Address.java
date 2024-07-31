package net.daniel.azevedo.meuwebsite.core.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class Address implements Serializable {

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;
}
