package exceptions;

/**
 * This exception is called when the player
 * tries to plow an already plowed tile
 */
public class TileAlreadyPlowedException extends Exception {
    public TileAlreadyPlowedException() { super("Tile is already plowed!"); }
}
