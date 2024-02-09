package net.d4y2k.seabattle.user.dto;

import lombok.Data;
import net.d4y2k.seabattle.user.role.Role;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID id;
    private String email;
    private String role;

    public UserDTO(UUID id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role.getAuthority();
    }

}
