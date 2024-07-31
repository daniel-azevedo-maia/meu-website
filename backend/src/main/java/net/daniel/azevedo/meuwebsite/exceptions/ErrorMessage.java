package net.daniel.azevedo.meuwebsite.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ErrorMessage {
    private int status;
    private String error;
    private String message;
    private String path;
    private String method;
    private LocalDateTime timestamp;

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.timestamp = LocalDateTime.now();
    }
}