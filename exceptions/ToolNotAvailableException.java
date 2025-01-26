package exceptions;

/**
 * This exception is called when a tool is currently unusable to the player.
 * A possible reason could be insufficient money.
 */
public class ToolNotAvailableException extends Exception {
    public ToolNotAvailableException() { super(); }
}
