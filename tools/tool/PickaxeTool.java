package tools.tool;

import tools.Tool;

/**
 * Represents the Pickaxe {@link Tool}<br />
 * This tool is used to remove rocks
 */
public class PickaxeTool extends Tool {
    private final int cost = 50;
    private final double xpGain = 15.0;

    public int getCost() { return cost; }
    public double getXpGain() { return xpGain; }

    @Override
    public void useTool() {

    }
}
