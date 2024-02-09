package net.d4y2k.seabattle.map.cell.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AddShipDTO {

    @NotNull(message = "shipId is required")
    private UUID shipId;

    @NotNull(message = "horizontalCoordinate is required")
    @Min(value = 0, message = "horizontalCoordinate must be positive")
    private int horizontalCoordinate;

    @NotNull(message = "verticalCoordinate is required")
    @Min(value = 0, message = "verticalCoordinate must be positive")
    private int verticalCoordinate;

}
