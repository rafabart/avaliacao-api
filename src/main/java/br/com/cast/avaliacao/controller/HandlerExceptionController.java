package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.exception.CategoryNotFoundException;
import br.com.cast.avaliacao.exception.StandardError;
import br.com.cast.avaliacao.exception.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(final CategoryNotFoundException e, final HttpServletRequest request) {

        final StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Objeto não encontrado!", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(final MethodArgumentNotValidException e, final HttpServletRequest request) {

        final ValidationError validationError = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação", e.getMessage(), request.getRequestURI());

        for (FieldError fieldError : e.getBindingResult().getFieldErrors())
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
    }
}
