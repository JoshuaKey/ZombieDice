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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartScene extends DisplayScene{
	private final int MAX_PLAYERS = 20;
	
	private StartSceneController control;
	private FlowPane playerBox;
	private ChoiceBox<String> diffBox;
	private VBox center;
	
	private ArrayList<PlayerField> playersList;
	private SimpleIntegerProperty playerAmo;

	public StartScene(int width, int height){
		WIDTH = width;
		HEIGHT = height;
		control = new StartSceneController(this);
		playersList = new ArrayList<PlayerField>();
		playerAmo = new SimpleIntegerProperty();
	}

	@Override
	public void scene(Stage s) {
		this.s = s;
		BorderPane root = new BorderPane();
		
		GridPane top = new GridPane();
		//top.setGridLinesVisible(true);
//		top.setHgap(10);
//		top.setVgap(10);
		top.setPadding(new Insets(10));
		top.setAlignment(Pos.CENTER);
	
		// title
		Label title = new Label("Zombie Dice");
		title.setId("title");
		title.setPadding(new Insets(10));
		top.add(title, 0, 0, 1, 2);
		
		// help button
		Button infoBtn = new Button("?");
		infoBtn.setId("infoButton");
		infoBtn.setAlignment(Pos.TOP_RIGHT);
		infoBtn.setOnAction(e -> control.createHelp());
		top.add(infoBtn, 1, 0);
		
		root.setTop(top);
		
		// center with text and players
		center = new VBox();
		center.setSpacing(10);
		center.setPadding(new Insets(10));
		center.setAlignment(Pos.TOP_CENTER);
		// how to center the text on new lines...
		// game sceen needs to have a flow pane...
		center.getChildren().add(new Text("Indicate who is playing"));
		
		playerBox = new FlowPane(Orientation.VERTICAL, 10, 10);
		playerBox.setPadding(new Insets(10));
		playerBox.setAlignment(Pos.TOP_CENTER);
		
		PlayerField player1 = new PlayerField(1);
		PlayerField player2 = new PlayerField(2);
		playersList.add(player1);playersList.add(player2);
		playerBox.getChildren().addAll(player1, player2);
		center.getChildren().add(playerBox);
		
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
					center.getChildren().remove(addPlayer);
				} else if(!center.getChildren().contains(addPlayer)){
					center.getChildren().add(center.getChildren().size()-1, addPlayer);
				}
				if(value <= 2){
					center.getChildren().remove(removePlayer);
				} else if(!center.getChildren().contains(removePlayer)){
					center.getChildren().add(removePlayer);
				}
			}
		});
		
		center.getChildren().addAll(addPlayer);
		
		root.setCenter(center);
		
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
		
		root.setBottom(bottom);
		
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.getStylesheets().add("StyleSheets/StyleSheet.css");
		s.setScene(scene);
		s.centerOnScreen();
	}
	
	public ArrayList<PlayerField> getPlayerList(){
		return playersList;
	}
	
	public FlowPane getPlayerBox(){
		return playerBox;
	}
	
	public int getDifficultySelection(){
		return diffBox.getSelectionModel().getSelectedIndex();
	}
	
	public SimpleIntegerProperty getPlayerAmo() {
		return playerAmo;
	}

}
