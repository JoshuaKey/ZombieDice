package Entry;

import Scene.StartScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXZombieDice extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StartScene scene = new StartScene();
		scene.scene(primaryStage);
		primaryStage.show();
	}
}
/*
 *  could I have scene classes that I could instantiate and get scenes from to update central class aka this one???
 * 
 * 
 */


// add a help button for info
// add a temporary display of past rolls for a player
// add a pixel art of losing
// remove reroll button if user can't reroll
// create a rolling animation??? when rolling dice
// use Thread.yield???? for pause
// use a constant size for display so it doesn't update...
// learn proper code design practices for communication betyween logic code and display code

