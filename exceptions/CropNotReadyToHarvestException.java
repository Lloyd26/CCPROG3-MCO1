package exceptions;

/**
 * This exception is called a crop is selected to be harvested
 * without meeting its minimum requirements
 */
public class CropNotReadyToHarvestException extends Exception {
    public CropNotReadyToHarvestException() { super(); }
}
