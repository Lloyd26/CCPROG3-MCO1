package crops.fruittrees;

import crops.Crop;
import crops.CropType;

/**
 * Represents Mango as a {@link Crop} of {@link CropType} Fruit Tree
 */
public class Mango implements Crop {
    private final String codeName = "MNG";

    private final String name = "Mango";

    private final CropType cropType = CropType.FRUIT_TREE;

    private final int harvestTime = 10;

    private final int waterNeeds = 7;
    private final int waterNeedsBonus = 7;

    private final int fertilizerNeeds = 4;
    private final int fertilizerNeedsBonus = 4;

    private final int productsProducedLow = 5;
    private final int productsProducedHigh = 15;

    private final int seedCost = 100;

    private final int sellPrice = 8;

    private final double xpYield = 25;

    public String getCodeName() {
        return null;
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
