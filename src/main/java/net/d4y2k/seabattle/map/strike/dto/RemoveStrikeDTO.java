package net.d4y2k.seabattle.map.strike.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class RemoveStrikeDTO {

    @NotNull(message = "userId is required")
    private UUID userId;

}
