package net.d4y2k.seabattle.map.cell.exception;

public class ShotsAreOverException extends RuntimeException{
    public ShotsAreOverException() {
        super("The shots are over");
    }
}
