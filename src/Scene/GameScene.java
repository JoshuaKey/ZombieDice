package Scene;

import Controller.GameSceneController;
import Players.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameScene extends DisplayScene{
	
	private ImageView[] dieImgs;
	
	private GameSceneController control;
	private Label[] playerLabel;
	private Label rollLabel;
	private Text winText;
	
	public GameScene(Player[] players, int difficulty){
		rollLabel = new Label();
		winText = new Text();
		dieImgs = new ImageView[3];
		for(int i = 0; i < dieImgs.length; i++){
			dieImgs[i] = new ImageView();
		}
		
		playerLabel = new Label[players.length];
		
		
		control = new GameSceneController(this, players, difficulty);
	}

	@Override
	public void scene(Stage s) {
		this.s = s;
		BorderPane root = new BorderPane();
		
		// top
		HBox top = new HBox();
		top.setPadding(new Insets(10,50,10,50));
		top.setSpacing(50);
		top.setAlignment(Pos.CENTER);
		
		//For Player turns...
		for(int i = 0; i < playerLabel.length; i++){
			playerLabel[i] = new Label(control.getPlayers()[i].getName() + " \n0 Brains");
		}
		playerLabel[0].setId("playerTurn");
		top.getChildren().addAll(playerLabel);
		root.setTop(top);	
		
		// center
		VBox center = new VBox();
		center.setPadding(new Insets(10));
		center.setSpacing(10);
		center.setAlignment(Pos.CENTER);
		
		// Indicates what is needed to win and who is winning
		winText.setText("You need 13 Brains to win\n");
		center.getChildren().add(winText);
		
		// dice images HBox
		HBox diceImg = new HBox();
		diceImg.setPadding(new Insets(50));
		diceImg.setSpacing(50);
		diceImg.setAlignment(Pos.CENTER);
		
		// add the actual image views
		diceImg.getChildren().addAll(dieImgs);
		center.getChildren().add(diceImg);
		
		// add a sentence of what they rolled
		center.getChildren().add(rollLabel);
		root.setCenter(center);
		
		// bottom
		HBox bottom = new HBox();
		bottom.setPadding(new Insets(10,50,10,50));
		bottom.setSpacing(50);
		bottom.setAlignment(Pos.CENTER);
		
		Button nextTurn = new Button("End Turn");
		nextTurn.setOnAction(e -> control.nextPlayerTurn());
		
		Button reroll = new Button("Reroll");
		reroll.setOnAction(e -> control.roll());
		bottom.getChildren().addAll(nextTurn, reroll);
		
		root.setBottom(bottom);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("StyleSheets/StyleSheet.css");
		s.setScene(scene);
		s.centerOnScreen();
	}
	
	public Text getWinText(){
		return winText;
	}
	
	public Label[] getPlayerLabels(){
		return playerLabel;
	}
	
	public Label getRollLabel(){
		return rollLabel;
	}
	
	public ImageView[] getDieImgs(){
		return dieImgs;
	}
	
	public void setPlayerLabels(Label[] labels){
		playerLabel = labels;
	}

}
