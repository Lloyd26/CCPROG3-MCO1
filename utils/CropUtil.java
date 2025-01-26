package utils;

import crops.Crop;
import crops.flowers.Rose;
import crops.flowers.Sunflower;
import crops.flowers.Tulips;
import crops.fruittrees.Apple;
import crops.fruittrees.Mango;
import crops.rootcrops.Carrot;
import crops.rootcrops.Potato;
import crops.rootcrops.Turnip;

import java.util.*;

/**
 * A utility class for the crops
 */
public class CropUtil {
    /**
     * @deprecated
     * Get a crop instance from a crop name
     * @param name the name of the crop
     * @return crop
     */
    @Deprecated
    public static Crop getCropFromName(String name) {
        final Map<String, Crop> crops = new HashMap<>();
        crops.put("Turnip", new Turnip());
        crops.put("Carrot", new Carrot());
        crops.put("Potato", new Potato());
        crops.put("Rose", new Rose());
        crops.put("Tulips", new Tulips());
        crops.put("Sunflower", new Sunflower());
        crops.put("Mango", new Mango());
        crops.put("Apple", new Apple());

        for (String s : crops.keySet()) {
            if (name.equals(s)) {
                return crops.get(name);
            }
        }

        return null;
    }

    /**
     * Get the number of similar crops
     * in the player's crop inventory
     * @param crops the player's crop inventory
     * @return {@code Map<Crop, Integer}, where {@link Crop} is the crop instance and {@code Integer} is the crop count
     */
    public static Map<Crop, Integer> getCropsCount(List<Crop> crops) {
        final Map<Crop, Integer> cropsCountMap = new HashMap<>();
        final Set<String> processedCropNamesSet = new HashSet<>();

        for (Crop c : crops) {
            if (processedCropNamesSet.contains(c.getName())) continue;

            cropsCountMap.put(c, (int) crops.stream().filter(crop -> crop.getName().equals(c.getName())).count());

            processedCropNamesSet.add(c.getName());

            //crops.removeIf(crop -> crop.getName().equals(c.getName()));
        }


        return cropsCountMap;
    }

    /**
     * Input for the crops currently selling in the market
     * @param choices A {@code Map<Character, Crop>} containing the {@code char} key and the {@link Crop} value
     * @param title the title to be displayed
     * @param message the message to be displayed
     * @return the selected crop to be bought
     */
    public static Crop askCrop(Map<Character, Crop> choices, String title, String message) {
        boolean inputValid = false;

        Scanner s = new Scanner(System.in);

        System.out.println("-----------[" + title.toUpperCase() + "]-----------");
        choices.forEach((x,y) -> System.out.println(" * [" + x.toString().toUpperCase() + "]: " + y.getName()));
        System.out.println("------------------------------");

        char response;

        Crop crop;

        do {
            System.out.print(message);
            response = s.next().charAt(0);
            for (char c : choices.keySet()) {
                if (String.valueOf(response).equalsIgnoreCase(String.valueOf(c))) {
                    inputValid = true;
                    return choices.get(Character.toUpperCase(response));
                }
            }
            if (!inputValid) {
                System.out.println("ERROR: Invalid input!");
            }
        } while (!inputValid);

        return choices.get(Character.toUpperCase(response));
    }

    /**
     * Input on which crop the player wants to plant
     * @param cropsCountMap A {@code Map<Crop, Integer>} containing the {@link Crop} and its count
     * @param message the message to be displayed
     * @return the selected crop to be planted
     */
    public static Crop askCropToPlant(Map<Crop, Integer> cropsCountMap, String message) {
        boolean inputValid = false;

        Scanner s = new Scanner(System.in);

        System.out.println("-----------[CROPS]------------");
        if (cropsCountMap.isEmpty()) {
            System.out.println("      You have no crops       ");
            System.out.println("      in your inventory.      ");
        } else {
            for (Crop c : cropsCountMap.keySet()) {
                System.out.println(" * [" + Character.toUpperCase(c.getName().charAt(0)) + "]: " + c.getName() + " (x" + cropsCountMap.get(c) + ")");
            }
        }
        System.out.println("------------------------------");

        String response;
        Crop crop = null;

        if (cropsCountMap.isEmpty()) return null;

        do {
            System.out.print(message);
            response = s.next();

            for (Crop c : cropsCountMap.keySet()) {
                if (response.equalsIgnoreCase(String.valueOf(Character.toUpperCase(c.getName().charAt(0))))) {
                    inputValid = true;
                    crop = c;
                    return crop;
                } else {
                    System.out.println("ERROR: Invalid input!");
                }
            }
        } while (!inputValid);

        return crop;
    }
}
