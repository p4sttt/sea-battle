package net.d4y2k.seabattle.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {


    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password id required")
    @Size(min = 5, max = 1337, message = "Password must contains from 5 and 1337 symbols")
    private String password;

    @NotBlank(message = "Password id required")
    @Size(min = 5, max = 1337, message = "Password must contains from 5 and 1337 symbols")
    private String passwordRepetition;

}
