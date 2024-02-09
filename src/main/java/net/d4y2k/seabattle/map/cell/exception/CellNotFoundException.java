package net.d4y2k.seabattle.map.cell.exception;

import net.d4y2k.seabattle.exceptions.EntityNotFoundException;

import java.util.UUID;

public class CellNotFoundException extends EntityNotFoundException {
    public CellNotFoundException() {
        super("Cell not found!");
    }

    public CellNotFoundException(UUID cellId) {
        super("Cell with id: " + cellId + " not found!");
    }
}
