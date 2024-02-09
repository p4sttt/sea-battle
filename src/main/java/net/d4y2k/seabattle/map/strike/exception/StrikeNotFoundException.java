package net.d4y2k.seabattle.map.strike.exception;

import net.d4y2k.seabattle.exceptions.EntityNotFoundException;

import java.util.UUID;

public class StrikeNotFoundException extends EntityNotFoundException {
    public StrikeNotFoundException() {
        super("Strike not found!");
    }

    public StrikeNotFoundException(UUID id) {
        super("Strike with id: " + id + " not found!");
    }
}
