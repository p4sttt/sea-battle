package net.d4y2k.seabattle.ship.exception;

import net.d4y2k.seabattle.exceptions.EntityNotFoundException;

import java.util.UUID;

public class ShipNotFoundException extends EntityNotFoundException {
    public ShipNotFoundException() {
        super("Ship not found!");
    }

    public ShipNotFoundException(UUID id) {
        super("Ship with id: " + id + " not found!");
    }
}
