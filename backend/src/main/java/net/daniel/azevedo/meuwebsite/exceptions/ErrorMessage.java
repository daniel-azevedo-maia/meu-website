package net.daniel.azevedo.meuwebsite.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class ErrorMessage {

    private String path;

    private String method;

    private int status;

    private String statusText;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;

    // Construtor padrão sem argumentos
    public ErrorMessage() {
    }

    // Construtor com detalhes do pedido, status e mensagem de erro
    public ErrorMessage(HttpServletRequest request,
                        HttpStatus status,
                        String message) {
        this.path = request.getRequestURI(); // Inicializa o caminho da URI do pedido
        this.method = request.getMethod(); // Inicializa o método HTTP do pedido
        this.status = status.value(); // Inicializa o código de status HTTP
        this.statusText = status.getReasonPhrase(); // Inicializa o texto do status HTTP
        this.message = message; // Inicializa a mensagem de erro
    }

    // Construtor com detalhes do pedido, status, mensagem de erro e resultado de validação
    public ErrorMessage(HttpServletRequest request,
                        HttpStatus status,
                        String message,
                        BindingResult result) {
        this.path = request.getRequestURI(); // Inicializa o caminho da URI do pedido
        this.method = request.getMethod(); // Inicializa o método HTTP do pedido
        this.status = status.value(); // Inicializa o código de status HTTP
        this.statusText = status.getReasonPhrase(); // Inicializa o texto do status HTTP
        this.message = message; // Inicializa a mensagem de erro
        addErrors(result); // Adiciona erros de validação ao mapa de erros
    }

    // Adiciona erros de validação ao mapa de erros
    private void addErrors(BindingResult result) {
        this.errors = new HashMap<>(); // Inicializa o mapa de erros
        for (FieldError fieldError : result.getFieldErrors()) {
            // Adiciona cada erro de campo ao mapa (campo -> mensagem de erro)
            this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}