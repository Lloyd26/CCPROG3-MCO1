package tools.tool;

import exceptions.ToolNotAvailableException;
import exceptions.IllegalToolUnoccupiedTileException;
import exceptions.IllegalToolWitheredTileException;
import farm.Tile;
import player.Player;
import tools.Tool;

/**
 * Represents the Watering Can {@link Tool}<br />
 * This tool is used to water a crop
 */
public class WateringCanTool extends Tool {
    private final int cost = 0;
    private final double xpGain = 0.5;

    private final Player player;
    private final Tile tile;

    public WateringCanTool(Player player, Tile tile) {
        this.player = player;
        this.tile = tile;
    }

    public int getCost() { return cost; }
    public double getXpGain() { return xpGain; }

    @Override
    public void useTool() throws ToolNotAvailableException, IllegalToolUnoccupiedTileException, IllegalToolWitheredTileException {
        if (!player.canUseTool(this)) throw new ToolNotAvailableException();
        if (!tile.isOccupied()) throw new IllegalToolUnoccupiedTileException();
        if (tile.isWithered()) throw new IllegalToolWitheredTileException();

        tile.water();

        player.subtractMoney(cost);
        player.addXP(xpGain);
    }
}
