package net.d4y2k.seabattle.map.strike.exception;

import java.util.UUID;

public class StrikeNotPossibleToDeleteException extends RuntimeException{
    public StrikeNotPossibleToDeleteException() {
        super("User is not possible to delete from map");
    }

    public StrikeNotPossibleToDeleteException(UUID strikeId) {
        super("Strike with id: " + strikeId + " is not possible to delete from map");
    }
}
