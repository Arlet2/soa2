package su.arlet.soa2.core.exceptions.handler;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import su.arlet.soa2.core.exceptions.EntityNotFoundException;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

     @ExceptionHandler(EntityNotFoundException.class)
     public ResponseEntity<Object> handleException(EntityNotFoundException e) {
         return ResponseEntity.notFound().build();
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<ValidationErrors> handleException(MethodArgumentNotValidException e) {
         ValidationErrors errors  = new ValidationErrors(e.getFieldErrors().stream()
                 .map(error -> new Error(error.getDefaultMessage()))
                 .toList());
         return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_XML).body(errors);
     }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleException(HttpMessageNotReadableException e) {
        Error error = new Error(e.getLocalizedMessage());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_XML).body(error);
    }





}

