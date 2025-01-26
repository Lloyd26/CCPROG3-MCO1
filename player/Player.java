package player;

import crops.Crop;
import exceptions.CannotAffordCropException;
import exceptions.FarmerLevelInsufficientException;
import tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private double money;
    private double xp;
    private int level;
    private FarmerType farmerType;

    private final List<Crop> cropInventory;

    public Player() {
        this.money = 100;
        this.xp = 0.0;
        this.level = 0;
        this.farmerType = FarmerType.FARMER;

        this.cropInventory = new ArrayList<>();
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void addMoney(double money) { this.money += money; }

    public void subtractMoney(double money) { this.money -= money; }

    public double getXP() {
        return xp;
    }

    // Automatically update level when XP is changed
    public void setXP(double xp) {
        this.xp = xp;
        this.level = (int) (xp/100);
    }

    public void addXP(double xpGain) {
        this.xp += xpGain;
        this.level = (int) (xp/100);
    }

    public int getLevel() {
        return level;
    }

    public FarmerType getFarmerType() {
        return farmerType;
    }

    public void setFarmerType(FarmerType farmerType) throws FarmerLevelInsufficientException {
        if (farmerType == FarmerType.REGISTERED_FARMER && level < 5) throw new FarmerLevelInsufficientException();
        if (farmerType == FarmerType.DISTINGUISHED_FARMER && level < 10) throw new FarmerLevelInsufficientException();
        if (farmerType == FarmerType.LEGENDARY_FARMER && level < 15) throw new FarmerLevelInsufficientException();

        this.farmerType = farmerType;
    }

    public boolean canUseTool(Tool tool) {
        return getMoney() >= tool.getCost();
    }

    public void addToCropInventory(Crop crop) {
        cropInventory.add(crop);
    }

    public void removeFromCropInventory(Crop crop) {
        cropInventory.remove(crop);
    }

    public boolean hasCropFromInventory(Crop crop) {
        return cropInventory.contains(crop);
    }

    public List<Crop> getCropInventory() {
        return cropInventory;
    }

    public boolean canBuyCrop(Crop crop) {
        return money >= crop.getSeedCost();
    }

    public void buyCrop(Crop crop) throws CannotAffordCropException {
        if (!canBuyCrop(crop)) throw new CannotAffordCropException();

        addToCropInventory(crop);
        money -= crop.getSeedCost();
    }
}
