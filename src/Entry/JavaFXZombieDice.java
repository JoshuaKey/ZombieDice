package Entry;

import Scene.StartScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXZombieDice extends Application {
	
	private final int WIDTH = 500, HEIGHT = 525;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StartScene scene = new StartScene(WIDTH, HEIGHT);
		scene.scene(primaryStage);
		primaryStage.show();
	}
}
// Make a flow Pane for the game players
// shoul i just make buttons visible and not move????
// fix cup class????
// chsange survibors to brains

// should I make a playerstsyem class which has convenient methods and labels and stuff??????

// add a help button for info ---

// add a temporary display of past rolls for a player
// add a pixel art of losing
// remove reroll button if user can't reroll
// create a rolling animation??? when rolling dice
// use Thread.yield???? for pause
// use a constant size for display so it doesn't update... ----
// learn proper code design practices for communication betyween logic code and display code

