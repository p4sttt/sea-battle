package net.d4y2k.seabattle.map.cell.dto;

import lombok.Data;
import net.d4y2k.seabattle.ship.Ship;
import net.d4y2k.seabattle.ship.dto.ShipDTO;

import java.util.UUID;

@Data
public class CellDTO {

    private UUID id;
    private int horizontalCoordinate;
    private int verticalCoordinate;
    private ShipDTO ship;

    public CellDTO(UUID id, int horizontalCoordinate, int verticalCoordinate, Ship ship) {
        this.id = id;
        this.horizontalCoordinate = horizontalCoordinate;
        this.verticalCoordinate = verticalCoordinate;
        this.ship = ship.toDTO();
    }
}
