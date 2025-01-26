package crops.rootcrops;

import crops.Crop;
import crops.CropType;

/**
 * Represents Potato as a {@link Crop} of {@link CropType} Root Crop
 */
public class Potato implements Crop {
    private final String codeName = "PTO";

    private final String name = "Potato";

    private final CropType cropType = CropType.ROOT_CROP;

    private final int harvestTime = 5;

    private final int waterNeeds = 3;
    private final int waterNeedsBonus = 4;

    private final int fertilizerNeeds = 1;
    private final int fertilizerNeedsBonus = 2;

    private final int productsProducedLow = 1;
    private final int productsProducedHigh = 10;

    private final int seedCost = 10;

    private final int sellPrice = 9;

    private final double xpYield = 7.5;

    public String getCodeName() {
        return codeName;
    }

    public String getName() {
        return name;
    }

    public CropType getCropType() {
        return cropType;
    }

    public int getHarvestTime() {
        return harvestTime;
    }

    public int getWaterNeeds() {
        return waterNeeds;
    }

    public int getWaterNeedsBonus() {
        return waterNeedsBonus;
    }

    public int getFertilizerNeeds() {
        return fertilizerNeeds;
    }

    public int getFertilizerNeedsBonus() {
        return fertilizerNeedsBonus;
    }

    public int getProductsProducedLow() {
        return productsProducedLow;
    }

    public int getProductsProducedHigh() {
        return productsProducedHigh;
    }

    public int getSeedCost() {
        return seedCost;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public double getXpYield() {
        return xpYield;
    }
}
