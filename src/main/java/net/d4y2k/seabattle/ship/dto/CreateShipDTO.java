package net.d4y2k.seabattle.ship.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateShipDTO {

    @NotNull(message = "Prize is required")
    private UUID prizeId;

}
