package net.daniel.azevedo.meuwebsite.core.domain;

import net.daniel.azevedo.meuwebsite.modules.user.domain.entities.User;

import java.time.LocalDateTime;

public interface Interaction {

    User getUsuario();
    LocalDateTime getDataInteracao();
    InteractionType getType();

}
