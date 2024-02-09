package net.d4y2k.seabattle.map.strike.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateStrikeDTO {

    @NotNull(message = "UserId is required")
    private UUID userId;

    @NotNull(message = "Strike count is required")
    @Min(value = 0, message = "Strike count must be more than 10")
    private int count;

}
