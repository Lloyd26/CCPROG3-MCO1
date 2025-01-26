package game;

import crops.Crop;
import crops.rootcrops.Turnip;
import exceptions.*;
import farm.FarmManager;
import farm.Tile;
import player.Player;
import utils.CropUtil;
import utils.Utils;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the manager class
 * for all the things related to the game
 */
public class GameManager {
    private Player player;
    private FarmManager farm;
    private GameStatus gameStatus;

    private int day;

    private final Map<Character, String> playerActions = new LinkedHashMap<>();

    private final int xAxis, yAxis;

    private String feedback;

    public GameManager(int xAxis, int yAxis) {
        //this.player = new Player();
        //this.farm = new FarmManager(xAxis, yAxis);
        this.gameStatus = GameStatus.STOP;

        this.xAxis = xAxis;
        this.yAxis = yAxis;

        day = 1;

        playerActions.put('B', "Buy seeds");
        playerActions.put('P', "Plant seeds");
        playerActions.put('A', "Advance day");
        playerActions.put('H', "Harvest crop");
        playerActions.put('O', "Plow tile");
        playerActions.put('W', "Water crop");
        playerActions.put('F', "Fertilize crop");
        //playerActions.put('X', "Exit game");
    }

    /**
     * Get the player of the game
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the farm instance
     * @return farm
     */
    public FarmManager getFarm() {
        return farm;
    }

    /**
     * Get tbe current {@link GameStatus}
     * @return gameStatus
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * Set the {@link GameStatus} and do tasks accordingly
     * @param gameStatus the new game status
     */
    public void setGameStatus(GameStatus gameStatus) {
        if (this.gameStatus == gameStatus) return;

        this.gameStatus = gameStatus;

        switch (gameStatus) {
            case START:
                System.out.println("------------------------------");
                System.out.println("            MyFarm            ");
                System.out.println("------------------------------");

                this.player = new Player();
                this.farm = new FarmManager(xAxis, yAxis);

                this.day = 1;

                break;
            case GAME_OVER:
                System.out.println("---------[GAME OVER]----------");
                if (Utils.choiceYesNo("Do you want to play again?")) {
                    setGameStatus(GameStatus.START);
                } else {
                    setGameStatus(GameStatus.STOP);
                }
                break;
            case STOP:
                System.out.println("Thank you for playing!");
                break;
        }
    }

    /**
     * Get the current day
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * Set a new value for the day
     * @param day the new day
     * @see #advanceDay()
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Advance a day
     */
    public void advanceDay() {
        this.day++;
    }

    /**
     * Print the feedback,
     * as well as the player details and tile/crop status
     */
    public void printFeedback() {
        System.out.println("\n");
        if (feedback != null) {
            System.out.println("Feedback: " + feedback + "\n");
            feedback = null;
        }
        System.out.println("-----------[PLAYER]-----------");
        System.out.println(" + Farmer Type: " + player.getFarmerType().getName());
        System.out.println(" + Object Coin: " + Double.valueOf(new DecimalFormat("#.##").format(player.getMoney())) + (player.getMoney() <= 0 ? " (!)" : ""));
        System.out.println(" + XP (LVL): " + player.getXP() + " (Level " + player.getLevel() + ")");
        System.out.println("-----------[STATUS]-----------");
        System.out.println(" + Day: " + day);
        System.out.println(" + Plant: " + (getFarm().getTile(1,1).isOccupied() ? getFarm().getTile(1,1).getCrop().getName() : "None"));
        System.out.println(" + Plowed: " + getFarm().getTile(1,1).isPlowed());
        System.out.println(" + Withered: " + getFarm().getTile(1,1).isWithered() + (getFarm().getTile(1, 1).isWithered() ? " (!)" : ""));
        System.out.println(" + Water Count: " + getFarm().getTile(1,1).getWaterTimes());
        System.out.println(" + Fertilizer Count: " + getFarm().getTile(1,1).getFertilizerTimes());
        System.out.println("------------------------------");
    }

    /**
     * Ask the player for the next action to take
     */
    public void inputPlayerAction() {
        char choice = Utils.choice(playerActions, "ACTION", "Enter player action: ");

        switch (choice) {
            // Buy seeds
            case 'B':
                System.out.println("");
                final Map<Character, Crop> marketChoices = new LinkedHashMap<>();
                marketChoices.put('T', new Turnip());
                Crop cropToBuy = CropUtil.askCrop(marketChoices, "MARKET", "Choose crop: ");

                try {
                    player.buyCrop(cropToBuy);
                    feedback = "The crop " + cropToBuy.getName() + " has been bought successfully!";
                } catch (CannotAffordCropException e) {
                    feedback = "You cannot afford that crop!";
                }
                break;

            // Plant seeds
            case 'P':
                System.out.println("");
                Map<Crop, Integer> cropsCountMap = CropUtil.getCropsCount(player.getCropInventory());

                Crop cropToPlant = CropUtil.askCropToPlant(cropsCountMap, "Enter crop to plant: ");

                if (cropToPlant == null) break;

                try {
                    getFarm().getTile(1,1).occupy(cropToPlant, day);
                    player.removeFromCropInventory(cropToPlant);
                    feedback = "The crop " + cropToPlant.getName() + " has been planted successfully!";
                } catch (TileNotPlowedException e) {
                    feedback = "You cannot plant that crop since that tile is unplowed!";
                } catch (TileAlreadyOccupiedException e) {
                    feedback = "You cannot plant into an already occupied tile!";
                }
                break;

            // Advance day
            case 'A':
                advanceDay();
                getFarm().updateAllTiles(day);
                feedback = "It is now day " + day + "!";
                break;

            // Harvest crop
            case 'H':
                try {
                    double[] harvestResult = getFarm().harvestCrop(player, 1, 1);
                    player.addMoney(harvestResult[1]);
                    player.addXP(getFarm().getTile(1, 1).getCrop().getXpYield());

                    feedback = "You have successfully harvested " + (int) harvestResult[0] + " " + getFarm().getTile(1, 1).getCrop().getName() + "(s) and made " + harvestResult[1] + ".";

                    getFarm().setTile(1, 1, new Tile(1, 1));
                } catch (IllegalToolUnoccupiedTileException e) {
                    feedback = "You cannot harvest an unoccupied tile!";
                } catch (IllegalToolWitheredTileException e) {
                    feedback = "You cannot harvest a withered tile!";
                } catch (CropNotReadyToHarvestException e) {
                    feedback = "You cannot harvest that crop since it is not yet ready for harvest!";
                }
                break;

            // Plow tile
            case 'O':
                //int[] coords = Utils.inputCoord("Enter tile coordinates");
                //getFarm().plowTile(player, coords[0], coords[1]);
                try {
                    getFarm().plowTile(player, 1, 1);
                    feedback = "Tile has been plowed successfully!";
                } catch (ToolNotAvailableException e) {
                    feedback = "You're currently unable to plow this tile!";
                } catch (TileAlreadyPlowedException e) {
                    feedback =  "Tile is already plowed!";
                }
                break;

            // Water crop
            case 'W':
                try {
                    getFarm().waterCrop(player, 1, 1);
                    feedback = "Tile has been watered successfully!";
                } catch (ToolNotAvailableException e) {
                    feedback = "You're currently unable to water this tile!";
                } catch (IllegalToolUnoccupiedTileException e) {
                    feedback = "You cannot water an unoccupied tile!";
                } catch (IllegalToolWitheredTileException e) {
                    feedback = "You cannot water a withered tile!";
                }
                break;

            // Fertilize crop
            case 'F':
                try {
                    getFarm().fertilizeCrop(player, 1, 1);
                    feedback = "Tile has been fertilized successfully!";
                } catch (ToolNotAvailableException e) {
                    feedback = "You're currently unable to fertilize this tile!";
                } catch (IllegalToolUnoccupiedTileException e) {
                    feedback = "You cannot fertilize an unoccupied tile!";
                } catch (IllegalToolWitheredTileException e) {
                    feedback = "You cannot fertilize a withered tile!";
                }
                break;

            // Exit game
            case 'X':
                setGameStatus(GameStatus.STOP);
                break;
        }

        // Check if game should be over every player action
        update();
    }

    /**
     * Automatically check if the game should be over
     */
    private void update() {
        int notWitheredTileCount = 0;

        for (Tile tile : getFarm().getTilesSet()) {
            if (!tile.isOccupied()) return;
            if (!tile.isWithered()) notWitheredTileCount++;
        }

        if (notWitheredTileCount == 0 || getPlayer().getMoney() <= 0) {
            printFeedback();
            System.out.println("");
            getFarm().printTiles();
            System.out.println("");

            if (notWitheredTileCount == 0) System.out.println("GAME OVER: All the tiles have been withered!");
            if (getPlayer().getMoney() <= 0) System.out.println("GAME OVER: You ran out of money!");
            System.out.println("");

            setGameStatus(GameStatus.GAME_OVER);
        }
    }
}
