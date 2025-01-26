package exceptions;

/**
 * This exception is called when the player tries to upgrade its {@link player.FarmerType}
 * without meeting the level requirement
 */
public class FarmerLevelInsufficientException extends Exception {
    public FarmerLevelInsufficientException() { super(); }
}
