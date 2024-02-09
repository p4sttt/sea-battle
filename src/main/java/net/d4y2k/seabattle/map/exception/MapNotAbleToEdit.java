package net.d4y2k.seabattle.map.exception;

import java.util.UUID;

public class MapNotAbleToEdit extends RuntimeException{
    public MapNotAbleToEdit() {
        super("Map not able to update or delete!");
    }
    public MapNotAbleToEdit(UUID mapId) {
        super("Map with id: " + mapId + " not able to update or delete!");
    }
}
