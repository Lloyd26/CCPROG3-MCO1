package crops;

/**
 * Represents an interface for all the crops<br />
 * This is used to reference the generic class of all crops
 */
public interface Crop {
    /**
     * Get the codename of a crop
     * @return codeName
     */
    public String getCodeName();

    /**
     * Get the name of a crop
     * @return name
     */
    public String getName();

    /**
     * Get the {@link CropType} of a crop
     * @return {@link CropType} cropType
     */
    public CropType getCropType();

    /**
     * Get the harvest time
     * or the day in which the crop can be harvested
     * @return harvestTime
     */
    public int getHarvestTime();

    /**
     * Get the water needs
     * or the minimum water level requirement
     * of a crop
     * @return waterNeeds
     */
    public int getWaterNeeds();

    /**
     * Get the water needs bonus
     * or the maximum water level limit
     * of a crop
     * @return waterNeedsBonus
     */
    public int getWaterNeedsBonus();

    /**
     * Get the fertilizer needs
     * or the minimum fertilizer level requirement
     * of a crop
     * @return fertilizerNeeds
     */
    public int getFertilizerNeeds();

    /**
     * Get the fertilizer needs bonus
     * or the maximum fertilizer level limit
     * of a crop
     * @return fertilizerNeedsBonus
     */
    public int getFertilizerNeedsBonus();

    /**
     * Get the lower bound value of the products
     * that can be produced by harvesting a crop
     * @return productsProducedLow
     */
    public int getProductsProducedLow();

    /**
     * Get the upper bound value of the products
     * that can be produced by harvesting a crop
     * @return productsProducedHigh
     */
    public int getProductsProducedHigh();

    /**
     * Get the cost of the seed
     * needed to buy the crop
     * @return seedCost
     */
    public int getSeedCost();

    /**
     * Get the base selling price
     * of a crop
     * @return sellPrice
     */
    public int getSellPrice();

    /**
     * Get the number of XP that will be gained
     * when a crop is successfully harvested
     * @return xpYield
     */
    public double getXpYield();
}
