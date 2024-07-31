package net.daniel.azevedo.meuwebsite.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.daniel.azevedo.meuwebsite.modules.post.domain.exceptions.*;
import net.daniel.azevedo.meuwebsite.modules.user.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        log.error("User not found exception - ", ex);
        return buildResponseEntity(request, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlePostNotFoundException(PostNotFoundException ex, HttpServletRequest request) {
        log.error("Post not found exception - ", ex);
        return buildResponseEntity(request, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(PostAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handlePostAlreadyExistsException(PostAlreadyExistsException ex, HttpServletRequest request) {
        log.error("Post already exists exception - ", ex);
        return buildResponseEntity(request, HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(PostNotAuthorizedException.class)
    public ResponseEntity<ErrorMessage> handlePostNotAuthorizedException(PostNotAuthorizedException ex, HttpServletRequest request) {
        log.error("Post not authorized exception - ", ex);
        return buildResponseEntity(request, HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(UserHasPostsException.class)
    public ResponseEntity<ErrorMessage> handleUserHasPostsException(UserHasPostsException ex, HttpServletRequest request) {
        log.error("User has posts exception - ", ex);
        return buildResponseEntity(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex, HttpServletRequest request) {
        log.error("General exception - ", ex);
        return buildResponseEntity(request, HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + ex.getMessage());
    }

    private ResponseEntity<ErrorMessage> buildResponseEntity(HttpServletRequest request, HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, status, message));
    }
}
