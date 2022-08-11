package dataManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * CLass responsible for handling file saving and reading
 */
public class DataManager {


    /**
     * Method used for writing winner score to the file
     *
     * @param winnerScore score of the winner
     */
  public void writeToFile(String winnerScore, String winnerName) {
        try {
            FileWriter fileWriter = new FileWriter("stats.txt", true);
            fileWriter.append(winnerScore).append(",").append(winnerName).append("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 

    /**
     * Method responsible for reading best scores from the file
     */
    public static void bestScoreRead() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("stats.txt"), StandardCharsets.UTF_8);
            bestScoreSort(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bestScoreSort(List<String> lines) {
        Map<Integer, String> scoreMap = new HashMap<>();
        System.out.println("Best scores: ");
        for (String line : lines) {
            String scoreLine = String.valueOf(line);
            String[] parts = String.valueOf(scoreLine).split(",");
            Integer score =Integer.valueOf(parts[0]);
            scoreMap.put(score, parts[1]);
        }
        Map<Integer, String> scoresMap = new TreeMap<>(scoreMap).descendingMap();
        printMap(scoresMap);
    }



    public static void printMap(Map<Integer,String> map) {
        Set<Map.Entry<Integer, String>> scoreSet= map.entrySet();
        for (Map.Entry<Integer, String> entry : scoreSet) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " => " + value);
        }
        System.out.println("========================");
    }

}
