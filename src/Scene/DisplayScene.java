package Scene;

import javafx.stage.Stage;

public abstract class DisplayScene {
	protected Stage s;
	protected int WIDTH, HEIGHT;
	
	public abstract void scene(Stage s);
	
	public Stage getStage(){
		return s;
	}
	public int getWidth(){
		return WIDTH;
	}
	public int getHeight(){
		return HEIGHT;
	}
}
