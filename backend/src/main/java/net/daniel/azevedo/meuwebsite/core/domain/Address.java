package net.daniel.azevedo.meuwebsite.core.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
@Embeddable
public class Address implements Serializable {

    private String street;
    private String number;
    private String zipCode;
    private String city;
    private String state;
}
