package net.d4y2k.seabattle.map.exception;

public class CellAlreadyBusy extends RuntimeException{
    public CellAlreadyBusy(int x, int y) {
        super("Cell with coordinate x=" + x + ", y=" + y + " already busy");
    }
}
