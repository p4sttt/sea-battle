package net.d4y2k.seabattle.user.exception;

import net.d4y2k.seabattle.exceptions.EntityAlreadyExistException;

import java.util.UUID;

public class UserAlreadyExistException extends EntityAlreadyExistException {
    public UserAlreadyExistException() {
        super("User already exist!");
    }

    public UserAlreadyExistException(UUID id) {
        super("User with id: " + id + " already exist!");
    }
}
