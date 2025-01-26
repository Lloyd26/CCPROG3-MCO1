package tools.tool;

import exceptions.*;
import farm.Tile;
import player.Player;
import tools.Tool;

/**
 * Represents the Fertilizer {@link Tool}<br />
 * This tool is used to give a crop fertilizer
 */
public class FertilizerTool extends Tool {
    private final int cost = 10;
    private final double xpGain = 4.0;

    private final Player player;
    private final Tile tile;

    public FertilizerTool(Player player, Tile tile) {
        this.player = player;
        this.tile = tile;
    }

    public int getCost() { return cost; }
    public double getXpGain() { return xpGain; }

    @Override
    public void useTool() throws ToolNotAvailableException, IllegalToolWitheredTileException, IllegalToolUnoccupiedTileException {
        if (!player.canUseTool(this)) throw new ToolNotAvailableException();
        if (!tile.isOccupied()) throw new IllegalToolUnoccupiedTileException();
        if (tile.isWithered()) throw new IllegalToolWitheredTileException();

        tile.fertilize();

        player.subtractMoney(cost);
        player.addXP(xpGain);
    }
}
