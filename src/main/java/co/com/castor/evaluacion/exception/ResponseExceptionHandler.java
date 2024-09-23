package co.com.castor.evaluacion.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleAllException(Exception ex, WebRequest request) {
        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        CustomErrorResponse err = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // Se obtienen errores de los campos
        List<String> errores = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        ValidationErrorResponse errors = new ValidationErrorResponse(errores);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
