package net.d4y2k.seabattle.user;

import jakarta.servlet.http.HttpServletRequest;
import net.d4y2k.seabattle.user.exception.UserAlreadyExistException;
import net.d4y2k.seabattle.user.exception.UserNotFoundException;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleUserNotFoundException(UserNotFoundException exception) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Response response = new Response(request.getRequestURI(), HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Response> handleUserNotFoundException(UserAlreadyExistException exception) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Response response = new Response(request.getRequestURI(), HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

}
