import java.io.*;
import java.util.Scanner;

public class ScoreKeeper {
    private int redWins; // Number of wins by the Red player
    private int blueWins; // Number of wins by the Blue player
    private int ties; // Number of ties

    // Constructor to initialize the ScoreKeeper and read existing scores
    public ScoreKeeper() {
        readScores(); // Read scores from the file
    }

    // Method to increment the number of wins by the Red player
    public void incrementRedWins() {
        redWins++;
    }

    // Method to increment the number of wins by the Blue player
    public void incrementBlueWins() {
        blueWins++;
    }

    // Method to increment the number of ties
    public void incrementTies() {
        ties++;
    }

    // Method to save the scores to a file
    public void saveScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("scoreTaker.txt"))) {
            writer.println("Red Wins: " + redWins);
            writer.println("Blue Wins: " + blueWins);
            writer.println("Ties: " + ties);
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace if an IOException occurs
        }
    }

    // Method to read scores from a file
    private void readScores() {
        try (Scanner scanner = new Scanner(new File("scoreTaker.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Red Wins: ")) {
                    redWins = Integer.parseInt(line.substring(10)); // Parse the number of Red wins
                } else if (line.startsWith("Blue Wins: ")) {
                    blueWins = Integer.parseInt(line.substring(11)); // Parse the number of Blue wins
                } else if (line.startsWith("Ties: ")) {
                    ties = Integer.parseInt(line.substring(6)); // Parse the number of ties
                }
            }
        } catch (FileNotFoundException e) {
            // Initialize scores to zero if the file is not found
            redWins = 0;
            blueWins = 0;
            ties = 0;
        }
    }
}
