package exceptions;

/**
 * This exception is called when the player
 * tries to buy a crop with insufficient money
 */
public class CannotAffordCropException extends Exception {
    public CannotAffordCropException() { super(); }
}
