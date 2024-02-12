package net.daniel.azevedo.meuwebsite.exception;

import lombok.Getter;

@Getter
public enum ProblemType {

    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    USUARIO_TEM_POSTS("/usuario-possui-posts", "O usuário possui posts associados");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "danielazevedo.net" + path;
        this.title = title;
    }

}
