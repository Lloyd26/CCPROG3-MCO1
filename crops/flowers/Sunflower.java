package crops.flowers;

import crops.Crop;
import crops.CropType;

/**
 * Represents Sunflower as a {@link Crop} of {@link CropType} Flower
 */
public class Sunflower implements Crop {
    private final String codeName = "SFL";

    private final String name = "Sunflower";

    private final CropType cropType = CropType.FLOWER;

    private final int harvestTime = 3;

    private final int waterNeeds = 2;
    private final int waterNeedsBonus = 3;

    private final int fertilizerNeeds = 1;
    private final int fertilizerNeedsBonus = 2;

    private final int productsProducedLow = 1;
    private final int productsProducedHigh = 1;

    private final int seedCost = 20;

    private final int sellPrice = 19;

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
