package tools;

import exceptions.*;

/**
 * Represents an interface for all the tiles<br />
 * This is used to reference the generic class of all tiles
 */
public abstract class Tool {
    private int cost;
    private double xpGain;

    /**
     * Get the cost needed
     * to use a tool
     * @return cost
     */
    public int getCost() { return cost; }

    /**
     * Get the amount of XP that will be gained
     * when the player uses a tool
     * @return
     */
    public double getXpGain() { return xpGain; }

    /**
     * Represents the implementation of the tool
     * @throws ToolNotAvailableException called when the player cannot use a tool
     * @throws TileAlreadyPlowedException called when the plow tool is used on an already plowed tile
     * @throws IllegalToolUnoccupiedTileException called when a tool is illegally used on an unoccupied tile
     * @throws IllegalToolWitheredTileException called when a tool is illegally used on a withered tile
     */
    public abstract void useTool() throws ToolNotAvailableException, TileAlreadyPlowedException, IllegalToolUnoccupiedTileException, IllegalToolWitheredTileException;
}
