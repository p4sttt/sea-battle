package net.d4y2k.seabattle.map.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateMapSizeDTO {

    @NotNull(message = "Size is required")
    @Min(value = 2, message = "Map size must be more than 2")
    @Max(value = 10, message = "Map size must be less then 10")
    private int size;

}
