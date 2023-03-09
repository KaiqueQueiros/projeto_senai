package trevo.agro.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import trevo.agro.api.exceptions.models.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationError(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ErrorDetails errorModel = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), getErrorsMap(errors));
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorDetails> notFoundException(NotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorDetails> globalExceptionHandler (Exception ex){
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}
