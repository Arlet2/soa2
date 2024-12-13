package su.arlet.soa2.core.exceptions.handler;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
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

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Error> handleException(IllegalArgumentException e) {
        Error error = new Error("Illegal argument "+ e.getMessage().toLowerCase());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_XML).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleException(HttpMessageNotReadableException e) {
        String detailedMessage = e.getLocalizedMessage();

        String userFriendlyMessage = extractUserFriendlyMessage(detailedMessage);

        Error error = new Error(userFriendlyMessage);
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_XML).body(error);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Error> handleException(SQLException e) {
        Error error = new Error(e.getMessage());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_XML).body(error);
    }

    @ExceptionHandler
    ResponseEntity<Error> handle(NoHandlerFoundException e) {
        Error error = new Error(e.getLocalizedMessage());
        return ResponseEntity.notFound().build();
    }

    private String extractUserFriendlyMessage(String detailedMessage) {
        if (detailedMessage != null) {
            // Remove Java-specific technical details
            if (detailedMessage.contains("java.lang")) {
                return "Invalid input: Check the provided values.";
            }
            if (detailedMessage.contains("Failed to convert")) {
                return "Invalid input format. Please provide data in the expected format.";
            }
        }
        // Default message if no specific pattern matches
        return "An error occurred. Please check your input.";
    }






}

