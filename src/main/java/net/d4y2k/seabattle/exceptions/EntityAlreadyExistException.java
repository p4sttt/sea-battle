package net.d4y2k.seabattle.exceptions;

public abstract class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException() {
        super("Entity already exist!");
    }

    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
