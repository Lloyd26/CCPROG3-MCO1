package tools.tool;

import tools.Tool;

/**
 * Represents the Shovel {@link Tool}<br />
 * This tool is used to remove withered crops
 */
public class ShovelTool extends Tool {
    private final int cost = 7;
    private final double xpGain = 2.0;

    public int getCost() { return cost; }
    public double getXpGain() { return xpGain; }

    @Override
    public void useTool() {

    }
}
