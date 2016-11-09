package Scene;

import Controller.EndSceneController;
import Players.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EndScene extends DisplayScene{
	
	private EndSceneController control;
	private Player winner;
	
	public EndScene(Player winner){
		this.winner = winner;
		control = new EndSceneController(this);
	}

	@Override
	public void scene(Stage s) {
		this.s = s;
		BorderPane root = new BorderPane();
		
		Label title = new Label(winner.getName() + " Wins!");
		title.setPadding(new Insets(10));
		title.setId("title");
		root.setTop(title);
		
		VBox center = new VBox();
		center.setPadding(new Insets(10));
		center.setSpacing(10);
		center.setAlignment(Pos.CENTER);
		
		Label playAgainLabel = new Label("Would you like to play again?");
		center.getChildren().add(playAgainLabel);
		
		Button playAgainButton = new Button("Play Again");
		playAgainButton.setOnAction(e -> control.playAgain());
		center.getChildren().add(playAgainButton);
		
		root.setCenter(center);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("StyleSheets/StyleSheet.css");
		s.setScene(scene);
		s.centerOnScreen();
	}

}
