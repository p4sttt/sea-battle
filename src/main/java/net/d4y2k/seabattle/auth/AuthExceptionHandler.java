package net.d4y2k.seabattle.auth;

import jakarta.servlet.http.HttpServletRequest;
import net.d4y2k.seabattle.auth.exception.LoginException;
import net.d4y2k.seabattle.auth.exception.SignupException;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Response> handleLoginException(
            LoginException exception
    ) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Response response = new Response(request.getRequestURI(), HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @ExceptionHandler(SignupException.class)
    public ResponseEntity<Response> handleSignupException(
            SignupException exception
    ) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Response response = new Response(request.getRequestURI(), HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

}
