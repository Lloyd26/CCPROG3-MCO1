package farm;

import crops.Crop;
import exceptions.TileAlreadyOccupiedException;
import exceptions.TileAlreadyPlowedException;
import exceptions.TileNotPlowedException;

/**
 * Represents a single tile in the farm
 */
public class Tile {
    private final int xAxis;
    private final int yAxis;

    private boolean isPlowed;
    private boolean isOccupied;
    private boolean isWithered;
    private boolean isReadyToHarvest;

    private int waterTimes;
    private int fertilizerTimes;

    private int dayPlanted;
    private int dayToHarvest;

    private Crop crop;

    public Tile(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;

        this.isPlowed = false;
        this.isOccupied = false;

        this.waterTimes = 0;
        this.fertilizerTimes = 0;

        this.crop = null;
    }

    /**
     * Get the x-axis location of the tile
     * @return xAxis
     */
    public int getxAxis() {
        return xAxis;
    }

    /**
     * Get the y-axis location of the tile
     * @return yAxis
     */
    public int getyAxis() {
        return yAxis;
    }

    /**
     * Get the plow status of the tile
     * @return isPlowed
     */
    public boolean isPlowed() {
        return isPlowed;
    }

    /**
     * Plow the tile
     * @throws TileAlreadyPlowedException called when the tile is already plowed
     */
    public void plow() throws TileAlreadyPlowedException {
        if (isPlowed) throw new TileAlreadyPlowedException();
        this.isPlowed = true;
    }

    /**
     * Get the occupied status of the tile
     * @return isOccupied
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Occupy the tile with a crop
     * @param crop the crop to be planted in the tile
     * @param dayPlanted the day at which the crop is planted
     * @throws TileNotPlowedException called when a crop is to be planted to an unplowed tile
     * @throws TileAlreadyOccupiedException called when a crop is to be planted to an already occupied tile
     */
    public void occupy(Crop crop, int dayPlanted) throws TileNotPlowedException, TileAlreadyOccupiedException {
        if (!isPlowed) throw new TileNotPlowedException();
        if (isOccupied()) throw new TileAlreadyOccupiedException();
        this.crop = crop;
        this.dayPlanted = dayPlanted;
        this.dayToHarvest = dayPlanted + crop.getHarvestTime();
        isOccupied = true;
    }

    /**
     * Get the crop in the tile
     * @return crop
     */
    public Crop getCrop() {
        return crop;
    }

    /**
     * Get the withered status of the tile
     * @return isWithered
     */
    public boolean isWithered() {
        return isWithered;
    }

    /**
     * Set the withered status of the tile
     * @param withered wither state
     * @see #update(int day)
     */
    private void setWithered(boolean withered) {
        isWithered = withered;
    }

    /**
     * Get the status of the tile
     * if the crop is ready to be harvested
     * @return isReadyToHarvest
     */
    public boolean isReadyToHarvest() {
        return isReadyToHarvest;
    }

    /**
     * Get the count for the number of times
     * a crop has been watered
     * @return waterTimes
     */
    public int getWaterTimes() {
        return waterTimes;
    }

    /**
     * Water the crop
     */
    public void water() {
        waterTimes++;
    }

    /**
     * Get the count for the number of times
     * a crop has been given fertilizer
     * @return fertilizerTimes
     */
    public int getFertilizerTimes() {
        return fertilizerTimes;
    }

    /**
     * Fertilize the crop
     */
    public void fertilize() {
        fertilizerTimes++;
    }

    /**
     * Automatically update
     * the crop's harvest status and wither status
     * @param day the current day
     */
    public void update(int day) {
        if (!isOccupied()) return;

        if (day == dayToHarvest && (waterTimes >= crop.getWaterNeeds() && fertilizerTimes >= crop.getFertilizerNeeds())) {
            this.isReadyToHarvest = true;
        }

        if (day > dayToHarvest ||
           (day == dayToHarvest && (waterTimes < crop.getWaterNeeds() || fertilizerTimes < crop.getFertilizerNeeds()))) {
            this.isWithered = true;
        }
    }
}
