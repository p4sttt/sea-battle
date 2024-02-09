package net.d4y2k.seabattle.map.cell.exception;

import java.util.UUID;

public class CellInMapNotFoundException extends RuntimeException{
    public CellInMapNotFoundException() {
        super("Could not found cell in map!");
    }

    public CellInMapNotFoundException(UUID cellId, UUID mapId) {
        super("Cell with id: " + cellId + " not found in Map with id: " + mapId + "!");
    }
}
