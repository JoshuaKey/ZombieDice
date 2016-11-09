package Controller;

import Scene.EndScene;
import Scene.StartScene;

public class EndSceneController {
	
	private EndScene scene;
	
	public EndSceneController(EndScene scene){
		this.scene = scene;
	}
	
	public void playAgain(){
		StartScene beginning = new StartScene();
		beginning.scene(scene.getStage());
	}

}
