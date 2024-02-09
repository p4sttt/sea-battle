package net.d4y2k.seabattle.user.exception;

import net.d4y2k.seabattle.exceptions.EntityNotFoundException;

import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(UUID userId) {
        super("User with id: " + userId + " not found!");
    }

    public UserNotFoundException() {
        super("User not found!");
    }

}
