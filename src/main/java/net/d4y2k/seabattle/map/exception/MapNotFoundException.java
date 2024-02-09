package net.d4y2k.seabattle.map.exception;

import net.d4y2k.seabattle.exceptions.EntityNotFoundException;

import java.util.UUID;

public class MapNotFoundException extends EntityNotFoundException {
    public MapNotFoundException() {
        super("Map not found!");
    }

    public MapNotFoundException(UUID id) {
        super("Map with id: " + id + " not found!");
    }
}
