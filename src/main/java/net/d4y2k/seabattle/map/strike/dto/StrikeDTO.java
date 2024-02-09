package net.d4y2k.seabattle.map.strike.dto;

import lombok.Data;
import net.d4y2k.seabattle.user.User;
import net.d4y2k.seabattle.user.dto.UserDTO;

import java.util.UUID;

@Data
public class StrikeDTO {

    private UUID id;
    private UserDTO user;
    private int count;

    public StrikeDTO(UUID id, User user, int count) {
        this.id = id;
        this.user = user.toDTO();
        this.count = count;
    }
}
