package su.arlet.soa2.core.exceptions.handler;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import su.arlet.soa2.core.exceptions.EntityNotFoundException;

import java.sql.SQLException;


@RestControllerAdvice
public class ApiExceptionHandler {

     @ExceptionHandler(EntityNotFoundException.class)
     public ResponseEntity<Object> handleException(EntityNotFoundException e) {
         return ResponseEntity.notFound().build();
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<ValidationErrors> handleException(MethodArgumentNotValidException e) {
         ValidationErrors errors  = new ValidationErrors(e.getFieldErrors().stream()
                 .map(error -> new Error(error.getField() +" " + error.getDefaultMessage()))
                 .toList());
         return ResponseEntity.unprocessableEntity().contentType(MediaType.APPLICATION_XML).body(errors);
     }

    @ExceptionHandler({HttpMessageNotReadableException.class, IllegalArgumentException.class})
    public ResponseEntity<Error> handleException(RuntimeException e) {
        Error error = new Error(e.getLocalizedMessage());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_XML).body(error);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Error> handleException(SQLException e) {
        Error error = new Error(e.getMessage());
        System.out.println(error.getReason());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_XML).body(error);
    }






}

