package Scene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelpScene extends DisplayScene{
	
	public HelpScene(int width, int height){
		WIDTH = width;
		HEIGHT = height;
	}

	@Override
	public void scene(Stage s) {
		this.s = s;
		
		BorderPane root = new BorderPane();
		
		Label title = new Label("Zombie Dice");
		title.setId("title");
		title.setPadding(new Insets(10));
		root.setTop(new StackPane(title));
	
		
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.getStylesheets().add("StyleSheets/StyleSheet.css");
		s.setScene(scene);
		s.centerOnScreen();
	}

}
