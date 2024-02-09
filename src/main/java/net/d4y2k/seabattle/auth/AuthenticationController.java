package net.d4y2k.seabattle.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.auth.dto.LoginRequest;
import net.d4y2k.seabattle.auth.dto.SignupRequest;
import net.d4y2k.seabattle.auth.exception.LoginException;
import net.d4y2k.seabattle.auth.exception.SignupException;
import net.d4y2k.seabattle.exceptions.ValidationException;
import net.d4y2k.seabattle.user.User;
import net.d4y2k.seabattle.user.UserService;
import net.d4y2k.seabattle.utils.JwtService;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    ResponseEntity<Response> login(
            @RequestBody @Valid LoginRequest loginRequest,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Optional<User> optionalUser = userService.getByEmail(loginRequest.getEmail());

        if (optionalUser.isEmpty()) {
            throw new LoginException();
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new LoginException();
        }

        String jwtToken = jwtService.createToken(user);

        Map<String, Object> data = new HashMap<>();
        data.put("token", jwtToken);

        Response response = new Response(
                request.getRequestURI(),
                HttpStatus.OK,
                "User has successfully logged in",
                data
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    ResponseEntity<Response> signup(
            @RequestBody @Valid SignupRequest signupRequest,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Optional<User> optionalUser = userService.getByEmail(signupRequest.getEmail());

        if (optionalUser.isPresent()) {
            throw new SignupException();
        }

        if (!signupRequest.getPassword().equals(signupRequest.getPasswordRepetition())) {
            throw new SignupException();
        }

        User user = new User(signupRequest.getEmail(), signupRequest.getPassword());
        User createdUser = userService.create(user);

        String jwtToken = jwtService.createToken(createdUser);

        Map<String, Object> data = new HashMap<>();
        data.put("token", jwtToken);
        data.put("user", createdUser.toDTO());

        Response response = new Response(
                request.getRequestURI(),
                HttpStatus.OK,
                "User has successfully created an account",
                data
        );

        return ResponseEntity.ok(response);
    }

}
