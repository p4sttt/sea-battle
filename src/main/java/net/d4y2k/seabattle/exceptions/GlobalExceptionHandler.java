package net.d4y2k.seabattle.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> handleValidationException(ValidationException exception) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Response response = new Response(request.getRequestURI(), HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<Response> handleAccessDeniedException(
            AccessDeniedException exception
    ) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Response response = new Response(request.getRequestURI(), HttpStatus.FORBIDDEN, exception.getMessage());
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

}
