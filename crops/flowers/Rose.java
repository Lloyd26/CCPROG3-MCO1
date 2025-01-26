package crops.flowers;

import crops.Crop;
import crops.CropType;

/**
 * Represents Rose as a {@link Crop} of {@link CropType} Flower
 */
public class Rose implements Crop {
    private final String codeName = "ROS";

    private final String name = "Rose";

    private final CropType cropType = CropType.FLOWER;

    private final int harvestTime = 1;

    private final int waterNeeds = 1;
    private final int waterNeedsBonus = 2;

    private final int fertilizerNeeds = 0;
    private final int fertilizerNeedsBonus = 1;

    private final int productsProducedLow = 1;
    private final int productsProducedHigh = 1;

    private final int seedCost = 5;

    private final int sellPrice = 5;

    private final double xpYield = 2.5;

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
