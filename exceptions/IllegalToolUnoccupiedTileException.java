package exceptions;

/**
 * This exception is called when a tool is used or an operation is performed
 * illegally to an unoccupied tile
 */
public class IllegalToolUnoccupiedTileException extends Exception {
    public IllegalToolUnoccupiedTileException() { super(); }
}
