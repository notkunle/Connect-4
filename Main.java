import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Scene menuScene, gameScene, quitScene; // Scenes for menu, game, and quit confirmation
    private Stage primaryStage; // Primary stage for the application
    private Game game; // Game instance
    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Connect 4");

        // Create and set up the menu scene
        setupMenuScene();

        // Create and set up the quit scene
        setupQuitScene();

        // Set the initial scene to the menu
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    // Method to set up the menu scene
    private void setupMenuScene() {
        Button singlePlayerButton = new Button("Single Player");
        singlePlayerButton.setOnAction(e -> startGame(true)); // Start single-player game

        Button twoPlayerButton = new Button("Two Player");
        twoPlayerButton.setOnAction(e -> startGame(false)); // Start two-player game
        // if the twoplayer button is clicked the startGame method 

        Button quitGameButton = new Button("Quit Game"); // Button for quitting
        quitGameButton.setOnAction(e -> primaryStage.setScene(quitScene)); // Switch to quit scene

        VBox menuLayout = new VBox(10); // Layout for menu scene with spacing
        menuLayout.getChildren().addAll(singlePlayerButton, twoPlayerButton, quitGameButton); // Add buttons to layout
        menuScene = new Scene(menuLayout, 300, 200); // Create menu scene with layout
    }

    // Method to set up the quit scene
    private void setupQuitScene() {
        Button confirmQuitButton = new Button("Yes, Quit");
        confirmQuitButton.setOnAction(e -> primaryStage.close()); // Close the application

        Button cancelQuitButton = new Button("No, Continue");
        cancelQuitButton.setOnAction(e -> primaryStage.setScene(menuScene)); // Return to menu scene

        VBox quitLayout = new VBox(10); // Layout for quit scene with spacing
        quitLayout.getChildren().addAll(confirmQuitButton, cancelQuitButton); // Add buttons to layout
        quitScene = new Scene(quitLayout, 300, 200); // Create quit scene with layout
    }

    // Method to start the game with the selected mode
    private void startGame(boolean isSinglePlayer) {
        game = new Game(isSinglePlayer); // Initialize game instance with mode
        BorderPane gameLayout = game.getBoardGUI(); // Get game board GUI layout
        gameScene = new Scene(gameLayout, 590, 590); // Create game scene with layout
        primaryStage.setScene(gameScene); // Switch to game scene
    }

    public static void main(String[] args) {
        launch(args); // Launch the application
    }
}
