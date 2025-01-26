package utils;

import main.Main;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Utility class
 */
public class Utils {

    /**
     * A function capable of displaying the possible choices and validating the input
     * @param choices A {@code Map<Character, String>} containing the possible choices with {@code Character} as the char input and {@code String} as the choice name
     * @param title the title to be displayed
     * @param message the message to be displayed
     * @return the {@code char} input
     */
    public static char choice(Map<Character, String> choices, String title, String message) {
        boolean inputValid = false;

        Scanner s = new Scanner(System.in);

        System.out.println("-----------[" + title.toUpperCase() + "]-----------");
        choices.forEach((x,y) -> System.out.println(" * [" + x.toString().toUpperCase() + "]: " + y));
        System.out.println("------------------------------");

        char response;

        do {
            System.out.print(message);
            response = s.next().charAt(0);
            for (char c : choices.keySet()) {
                if (String.valueOf(response).equalsIgnoreCase(String.valueOf(c))) {
                    inputValid = true;
                    return Character.toUpperCase(response);
                }
            }
            if (!inputValid) {
                System.out.println("ERROR: Invalid input!");
            }
        } while (!inputValid);

        return Character.toUpperCase(response);
    }

    /**
     * A simple choice function with only "Yes" or "No" as the choices
     * @param message the message to be displayed
     * @return the Yes/No choice of the player
     */
    public static boolean choiceYesNo(String message) {
        boolean inputValid = false;

        Scanner s = new Scanner(System.in);

        char response;
        boolean choice = false;

        do {
            System.out.print(message + " (Y|N): ");
            response = s.next().charAt(0);
            if (String.valueOf(response).equalsIgnoreCase("Y") || String.valueOf(response).equalsIgnoreCase("N")) {
                if (String.valueOf(response).equalsIgnoreCase("Y")) choice = true;
                else if (String.valueOf(response).equalsIgnoreCase("N")) choice = false;

                inputValid = true;
            } else {
                System.out.println("ERROR: Invalid input!");
            }
        } while (!inputValid);

        return choice;
    }

    /**
     * A function that allows for coordinates input
     * @param message the message to be displayed
     * @return {@code int[0]} = X, {@code int[1]} = Y
     */
    public static int[] inputCoord(String message) {
        boolean inputValid = false;

        Scanner s = new Scanner(System.in);

        int[] coords = new int[2];

        do {
            System.out.print(message + " (X,Y): ");

            String response = s.nextLine();
            String strX = response.split(",")[0];
            String strY = response.split(",")[1];

            if (isStringInteger(strX) && isStringInteger(strY)) {
                int x = Integer.parseInt(strX);
                int y = Integer.parseInt(strY);
                if (x > 0 && x <= Main.getGameManager().getFarm().getXAxis() &&
                    y > 0 && y <= Main.getGameManager().getFarm().getYAxis()) {
                    coords[0] = Integer.parseInt(strX);
                    coords[1] = Integer.parseInt(strY);
                    inputValid = true;
                } else {
                    System.out.println("ERROR: Invalid input!");
                }
            } else {
                System.out.println("ERROR: Invalid input!");
            }
        } while (!inputValid);

        return coords;
    }

    /**
     * Function to check if a String is an integer using regex
     * @param s the string
     * @return the result of the regex match
     */
    public static boolean isStringInteger(String s) {
        return s.matches("-?\\d+");
    }

    private static final Random random = new Random();

    /**
     * Function to generate a random value from a given range
     * @param min the minimum value of the range
     * @param max the maximum value of the range
     * @return random generated value from the range
     */
    public static int randomFromRange(int min, int max) {
        int range = max - min + 1;
        return random.nextInt(range) + min;
    }
}
