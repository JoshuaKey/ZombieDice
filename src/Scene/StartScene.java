package Scene;

import java.util.ArrayList;

import Controller.StartSceneController;
import UserNodes.PlayerField;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartScene extends DisplayScene{
	
	private final int MAX_PLAYERS = 8;
	private StartSceneController control;
	private ArrayList<PlayerField> playersList;
	private VBox playerBox;
	private ChoiceBox<String> diffBox;
	private SimpleIntegerProperty playerAmo;

	public StartScene(){
		control = new StartSceneController(this);
		playersList = new ArrayList<PlayerField>();
		playerAmo = new SimpleIntegerProperty();
	}

	@Override
	public void scene(Stage s) {
		this.s = s;
		Pane root = new Pane();
		//root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		BorderPane display = new BorderPane();
		root.getChildren().add(display);
	
		// title
		Label title = new Label("Zombie Dice");
		title.setId("title");
		title.setPadding(new Insets(10));
		display.setTop(new StackPane(title));
		
		// center with text and players
		playerBox = new VBox();
		playerBox.setSpacing(10);
		playerBox.setPadding(new Insets(10));
		playerBox.setAlignment(Pos.TOP_CENTER);
		playerBox.getChildren().add(new Label("Indicate who is playing"));
		
		PlayerField player1 = new PlayerField(1);
		PlayerField player2 = new PlayerField(2);
		playersList.add(player1);playersList.add(player2);
		playerBox.getChildren().addAll(playersList);
		
		Button addPlayer = new Button("Add Player");
		addPlayer.setOnAction(e -> control.addPlayer());
		
		Button removePlayer = new Button("Remove Player");
		removePlayer.setOnAction(e -> control.removePlayer());
		
		// I could make it so that it has like 8 display squares and then add the details for a player 
		// by clicking addPlayer button. 
		// This would make it so changing the stage size isn't that big a deal because it will be set at 
		// the maximum size possible
		//It might also help transition to the Game Scene because it's probably going to be big.
		
		playerAmo.set(playersList.size());
		playerAmo.addListener(new ChangeListener(){
			@Override
			public void changed(ObservableValue observale, Object oldValue, Object newValue){
				Integer value = (Integer) newValue;
				if(value >= MAX_PLAYERS){
					playerBox.getChildren().remove(addPlayer);
				} else if(!playerBox.getChildren().contains(addPlayer)){
					playerBox.getChildren().add(playerBox.getChildren().size()-1, addPlayer);
				}
				if(value <= 2){
					playerBox.getChildren().remove(removePlayer);
				} else if(!playerBox.getChildren().contains(removePlayer)){
					playerBox.getChildren().add(removePlayer);
				}
			}
		});
		
		playerBox.getChildren().addAll(addPlayer);
		
		display.setCenter(playerBox);
		
		// play button and center
		Button playBtn = new Button("Play");
		playBtn.setOnAction(e -> control.createGame());
		
		// difficulty options
		Label diffLabel = new Label("Difficulty: ");
		diffBox = new ChoiceBox<String>(FXCollections.observableArrayList(
				"Easy", "Standard", "Hard"
		));
		diffBox.getSelectionModel().select(1);
		
		//right side
		HBox rightSide = new HBox();
		rightSide.setAlignment(Pos.CENTER_RIGHT);
		rightSide.getChildren().addAll(diffLabel, diffBox);
		
		//Bottom 
		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.setPadding(new Insets(10));
		bottom.setSpacing(10);
		bottom.getChildren().addAll(playBtn, rightSide);
		
		display.setBottom(bottom);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("StyleSheets/StyleSheet.css");
		s.setScene(scene);
		s.centerOnScreen();
	}
	
	public ArrayList<PlayerField> getPlayerList(){
		return playersList;
	}
	
	public VBox getPlayerDisplay(){
		return playerBox;
	}
	
	public int getDifficultySelection(){
		return diffBox.getSelectionModel().getSelectedIndex();
	}
	
	public SimpleIntegerProperty getPlayerAmo() {
		return playerAmo;
	}

}
