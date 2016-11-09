package Scene;

import javafx.stage.Stage;

public abstract class DisplayScene {
	protected Stage s;
	
	public abstract void scene(Stage s);
	
	public Stage getStage(){
		return s;
	}
}
