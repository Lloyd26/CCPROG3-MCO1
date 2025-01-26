package exceptions;

/**
 * This exception is called when the player tries
 * to plant a crop into an already occupied tile
 */
public class TileAlreadyOccupiedException extends Exception {
    public TileAlreadyOccupiedException() { super(); }
}
