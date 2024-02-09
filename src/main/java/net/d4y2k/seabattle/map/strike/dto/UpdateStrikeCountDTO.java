package net.d4y2k.seabattle.map.strike.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStrikeCountDTO {

    @NotNull(message = "count is required")
    @Min(value = 0, message = "value mast be positive")
    private int count;

}
