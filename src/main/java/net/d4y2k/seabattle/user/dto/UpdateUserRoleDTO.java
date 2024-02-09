package net.d4y2k.seabattle.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateUserRoleDTO {

    @NotBlank(message = "Authority is required")
    @Pattern(regexp = "USER|ADMIN", message = "Authority must be either USER or ADMIN")
    private String authority;

}
