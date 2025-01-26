package main;

import game.GameManager;
import game.GameStatus;

/**
 * MyFarm
 *
 * @author Matthew Adrian U. Chua
 * @author Lloyd Dominic G. Refuerzo
 */
public class Main {
    private static final GameManager gameManager = new GameManager(1, 1);

    public static void main(String[] args) {
        gameManager.setGameStatus(GameStatus.START);

        while (gameManager.getGameStatus() == GameStatus.START) {
            gameManager.printFeedback();
            System.out.println("");
            gameManager.getFarm().printTiles();
            System.out.println("");
            gameManager.inputPlayerAction();
        }
    }

    /**
     * Get the static instance of {@link GameManager}
     * @return gameManager
     */
    public static GameManager getGameManager() {
        return gameManager;
    }
}
