package tools.tool;

import exceptions.TileAlreadyPlowedException;
import exceptions.ToolNotAvailableException;
import farm.Tile;
import player.Player;
import tools.Tool;

/**
 * Represents the Plow {@link Tool}<br />
 * This tool is used to plow a tile
 */
public class PlowTool extends Tool {
    private final int cost = 0;
    private final double xpGain = 0.5;

    private final Player player;
    private final Tile tile;

    public PlowTool(Player player, Tile tile) {
        this.player = player;
        this.tile = tile;
    }

    public int getCost() { return cost; }
    public double getXpGain() { return xpGain; }

    @Override
    public void useTool() throws ToolNotAvailableException, TileAlreadyPlowedException {
        if (!player.canUseTool(this)) throw new ToolNotAvailableException();

        tile.plow();

        player.subtractMoney(cost);
        player.addXP(xpGain);
    }
}
