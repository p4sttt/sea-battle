package net.d4y2k.seabattle.exceptions;

public abstract class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException() {
        super("Entity not found!");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
