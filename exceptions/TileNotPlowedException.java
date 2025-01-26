package exceptions;

/**
 * This exception is called when the player
 * tries to perform an action to an unplowed tile
 */
public class TileNotPlowedException extends Exception {
    public TileNotPlowedException() { super(); }
}
