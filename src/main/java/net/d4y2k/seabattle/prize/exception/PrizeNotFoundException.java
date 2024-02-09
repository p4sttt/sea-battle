package net.d4y2k.seabattle.prize.exception;

import net.d4y2k.seabattle.exceptions.EntityNotFoundException;

import java.util.UUID;

public class PrizeNotFoundException extends EntityNotFoundException {
    public PrizeNotFoundException() {
        super("Prize not found!");
    }

    public PrizeNotFoundException(UUID id) {
        super("Prize with id: " + id + " not found!");
    }
}
