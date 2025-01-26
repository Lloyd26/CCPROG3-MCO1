package player;

/**
 * An {@code enum} for the different farmer types
 */
public enum FarmerType {
    FARMER("Farmer", 0, 0, 0, 0, 0, 0),
    REGISTERED_FARMER("Registered Farmer", 5, 1, -1, 0, 0, 200),
    DISTINGUISHED_FARMER("Distinguished Farmer", 10, 2, -2, 1, 0, 300),
    LEGENDARY_FARMER("Legendary Farmer", 15, 4, -3, 2, 1, 400);

    private String name;

    private int levelReq;
    private int bonusEarningsPerProduce;
    private int seedCostReduction;
    private int waterBonusLimitIncrease;
    private int fertilizerBonusLimitIncrease;
    private int registrationFee;

    private FarmerType(String name, int levelReq, int bonusEarningsPerProduce,
                        int seedCostReduction, int waterBonusLimitIncrease,
                        int fertilizerBonusLimitIncrease, int registrationFee) {
        this.name = name;
        this.levelReq = levelReq;
        this.bonusEarningsPerProduce = bonusEarningsPerProduce;
        this.seedCostReduction = seedCostReduction;
        this.waterBonusLimitIncrease = waterBonusLimitIncrease;
        this.fertilizerBonusLimitIncrease = fertilizerBonusLimitIncrease;
        this.registrationFee = registrationFee;
    }

    /**
     * Get the name of the farmer type
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the level requirement needed
     * to register or level up to a farmer type
     * @return levelReq
     */
    public int getLevelReq() {
        return levelReq;
    }

    /**
     * Get the bonus earnings per produce
     * of the farmer type
     * @return bonusEarningsPerProduce
     */
    public int getBonusEarningsPerProduce() {
        return bonusEarningsPerProduce;
    }

    /**
     * Get the seed cost reduction
     * of the farmer type
     * @return seedCostReduction
     */
    public int getSeedCostReduction() {
        return seedCostReduction;
    }

    /**
     * Get the water bonus limit increase
     * of the farmer type
     * @return waterBonusLimitIncrease
     */
    public int getWaterBonusLimitIncrease() {
        return waterBonusLimitIncrease;
    }

    /**
     * Get the fertilizer bonus limit increase
     * of the farmer type
     * @return fertilizerBonusLimitIncrease
     */
    public int getFertilizerBonusLimitIncrease() {
        return fertilizerBonusLimitIncrease;
    }

    /**
     * Get the registration fee needed
     * to register to a farmer type
     * @return registrationFee
     */
    public int getRegistrationFee() {
        return registrationFee;
    }
}
