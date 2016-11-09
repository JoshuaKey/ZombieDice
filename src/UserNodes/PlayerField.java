package UserNodes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PlayerField extends VBox{
	
	private Label playerLabel;
	private TextField playerInput;

	public PlayerField(int num){
		playerLabel = new Label("Player " + num + " name:");
		playerLabel.setAlignment(Pos.CENTER);
		
		playerInput = new TextField();
		playerInput.prefWidthProperty().bind(playerLabel.widthProperty().multiply(1.5f));
		playerInput.minWidthProperty().bind(playerLabel.widthProperty());
		playerInput.maxWidthProperty().bind(playerLabel.widthProperty().multiply(2));
		
		
		this.getChildren().addAll(playerLabel, playerInput);
		this.setAlignment(Pos.CENTER);
	}
	
	public String getPlayerInput(){
		return playerInput.getText();
	}
	
}
