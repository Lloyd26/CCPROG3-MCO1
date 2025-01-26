package farm;

import crops.Crop;
import crops.CropType;
import exceptions.*;
import player.Player;
import tools.tool.FertilizerTool;
import tools.tool.PlowTool;
import tools.tool.WateringCanTool;
import utils.Utils;

import java.util.*;

/**
 * Represents the manager class for managing the farm
 */
public class FarmManager {
    private final int xAxis;
    private final int yAxis;

    // Map: <X, Y> : Tile
    private final Map<Map.Entry<Integer, Integer>, Tile> tiles = new HashMap<>();

    public FarmManager(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;

        for (int x = 1; x <= xAxis; x++) {
            for (int y = 1; y <= yAxis; y++) {
                tiles.put(new AbstractMap.SimpleEntry<>(x, y), new Tile(x, y));
            }
        }
    }

    /**
     * Get the x-axis size of the {@link Tile} grid
     * @return xAxis
     */
    public int getXAxis() {
        return xAxis;
    }

    /**
     * Get the y-axis size of the {@link Tile} grid
     * @return yAxis
     */
    public int getYAxis() {
        return yAxis;
    }

    /**
     * Get all the tiles as a set
     * @return the set containing all the tiles in the farm
     * @see #getTile(int xAxis, int yAxis)
     */
    public Set<Tile> getTilesSet() {
        return new HashSet<>(tiles.values());
    }

    /**
     * Get a specific tile located in the {@code xAxis} and {@code yAxis}
     * @param xAxis the x-axis location of the tile
     * @param yAxis the y-axis location of the tile
     * @return tile
     * @see #getTilesSet()
     */
    public Tile getTile(int xAxis, int yAxis) {
        for (Map.Entry<Integer, Integer> entry : tiles.keySet()) {
            if (entry.getKey() == xAxis && entry.getValue() == yAxis) {
                return tiles.get(entry);
            }
        }
        return null;
    }

    /**
     * Set a new tile in the {@code xAxis} and {@code yAxis} location
     * @param xAxis the x-axis location of the tile to be set
     * @param yAxis the y-axis location of the tile to be set
     * @param tile the new tile
     */
    public void setTile(int xAxis, int yAxis, Tile tile) {
        tiles.put(new AbstractMap.SimpleEntry<>(xAxis, yAxis), tile);
    }

    /**
     * Print all the tiles in a grid
     */
    public void printTiles() {
        for (int i = 1; i <= xAxis; i++) {
            System.out.print("+-----");
        }
        System.out.println("+");

        int drawY = 1;
        for (int y = 1; y <= yAxis * 2; y++) {
            for (int x = 1; x <= xAxis; x++) {
                if (y % 2 != 0) {
                    Tile tile = getTile(x, drawY);

                    if (!tile.isPlowed()) {
                        System.out.print("|     ");
                    } else if (!tile.isOccupied()) {
                        System.out.print("| ~~~ ");
                    } else if (tile.isWithered()) {
                        System.out.print("| WTD ");
                    } else {
                        System.out.print("| " + tile.getCrop().getCodeName() + " ");
                    }
                } else {
                    System.out.print("+-----");
                }
            }

            if (y % 2 != 0) {
                System.out.println("|");
                drawY++;
            } else {
                System.out.println("+");
            }
        }
    }

    /**
     * Plow a tile
     * @param player the reference to the player
     * @param xAxis the x-axis location of the tile to be plowed
     * @param yAxis the y-axis location of the tile to be plowed
     * @throws ToolNotAvailableException called when the player cannot plow the tile
     * @throws TileAlreadyPlowedException called when the tile is already plowed
     */
    public void plowTile(Player player, int xAxis, int yAxis) throws ToolNotAvailableException, TileAlreadyPlowedException {
        new PlowTool(player, getTile(xAxis, yAxis)).useTool();
    }

    /**
     * Water a crop
     * @param player the reference to the player
     * @param xAxis the x-axis location of the tile to be watered
     * @param yAxis the y-axis location of the tile to be watered
     * @throws ToolNotAvailableException called when the player cannot water a crop
     * @throws IllegalToolUnoccupiedTileException called when the player tries to water an unoccupied tile
     * @throws IllegalToolWitheredTileException called when the player tries to water a withered tile
     */
    public void waterCrop(Player player, int xAxis, int yAxis) throws ToolNotAvailableException, IllegalToolUnoccupiedTileException, IllegalToolWitheredTileException {
        new WateringCanTool(player, getTile(xAxis, yAxis)).useTool();
    }

    /**
     * Fertilize a crop
     * @param player the reference to the player
     * @param xAxis the x-axis location of the tile to be fertilized
     * @param yAxis the y-axis location of the tile to be fertilized
     * @throws ToolNotAvailableException called when the player cannot fertilize a crop
     * @throws IllegalToolUnoccupiedTileException called when the player tries to fertilize an unoccupied tile
     * @throws IllegalToolWitheredTileException called when the payer tries to fertilize a withered tile
     */
    public void fertilizeCrop(Player player, int xAxis, int yAxis) throws ToolNotAvailableException, IllegalToolUnoccupiedTileException, IllegalToolWitheredTileException {
        new FertilizerTool(player, getTile(xAxis, yAxis)).useTool();
    }

    /**
     *
     * @param player the reference to the player
     * @param xAxis the x-coordinate location for the tile
     * @param yAxis the y-coordinate location for the tile
     * @return {@code double[0]} = products produced,<br /> {@code double[1]} = earnings in total
     * @throws IllegalToolUnoccupiedTileException thrown when tile is unoccupied
     * @throws IllegalToolWitheredTileException thrown when tile is withered
     * @throws CropNotReadyToHarvestException thrown when tile is not ready to harvest
     */
    public double[] harvestCrop(Player player, int xAxis, int yAxis) throws IllegalToolUnoccupiedTileException, IllegalToolWitheredTileException, CropNotReadyToHarvestException {
        Tile tile = getTile(xAxis, yAxis);

        if (!tile.isOccupied()) throw new IllegalToolUnoccupiedTileException();
        if (tile.isWithered()) throw new IllegalToolWitheredTileException();
        if (!tile.isReadyToHarvest()) throw new CropNotReadyToHarvestException();

        int harvestTotal;
        double waterBonus;
        double fertilizerBonus;
        double finalHarvestPrice;

        Crop crop = tile.getCrop();

        int productsProduced = Utils.randomFromRange(crop.getProductsProducedLow(), crop.getProductsProducedHigh());

        // Compute harvestTotal
        harvestTotal = productsProduced * (crop.getSellPrice() + player.getFarmerType().getBonusEarningsPerProduce());

        // Compute waterBonus
        waterBonus = harvestTotal * 0.2 * (Math.min(tile.getWaterTimes(), crop.getWaterNeedsBonus()) - 1);

        // Compute fertilizerBonus
        fertilizerBonus = harvestTotal * 0.5 * Math.min(tile.getFertilizerTimes(), crop.getFertilizerNeedsBonus());

        // Compute finalHarvestPrice
        finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
        if (crop.getCropType() == CropType.FLOWER) finalHarvestPrice *= 1.1;

        // harvestReturn[0] = products produced
        // harvestReturn[1] = earnings in total
        double[] harvestReturn = new double[2];
        harvestReturn[0] = productsProduced;
        harvestReturn[1] = finalHarvestPrice;

        return harvestReturn;
    }

    /**
     * Update all tiles by checking
     * if the crop can already be harvested
     * or if the crop should wither
     * @param day the current day
     */
    public void updateAllTiles(int day) {
        for (Tile tile : getTilesSet()) {
            tile.update(day);
        }
    }
}
