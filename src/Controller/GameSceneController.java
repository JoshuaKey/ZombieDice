package Controller;

import java.util.ArrayList;

import Die.Cup;
import Die.Die;
import Enum.RollValue;
import Players.Player;
import Players.PlayerComparator;
import Scene.EndScene;
import Scene.GameScene;
import Textures.Textures;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameSceneController {
	
	private GameScene scene;
	private Textures textures;
	private Player[] players;
	private Player current, winner;
	private Cup cup;
	private Die[] dies;
	
	private ArrayList<Player> tiedPlayers;
	private int winningBrains, initWinIndex, initBrains;
	private int survivorsCornered, shotgunBlasts;
	private int playerTurn;
	private boolean hasWon, isTied;
	
	public GameSceneController(GameScene scene, Player[] players, int diff){
		this.scene = scene;
		this.players = players;
		this.cup = new Cup(diff);
		this.dies = new Die[3];
		
		textures = new Textures();
		
		winningBrains = 13;
		tiedPlayers = new ArrayList<Player>();
		
		current = players[0];
		playerTurn = 0;
		roll();
	}
	
	public void nextPlayerTurn(){
		if(shotgunBlasts < 3){
			current.increaseBrains(survivorsCornered);
		}
		survivorsCornered = 0;
		shotgunBlasts = 0;
		dies = new Die[3];
		cup.reset();
		
		checkWin();
	
		Label[] playerLabels = scene.getPlayerLabels();
		Label playerLabel = playerLabels[playerTurn];
		playerLabel.setText(current.getName() + "\n" + current.getBrains() + " Brains");
		playerLabel.setId("");

		 // go to next person if there label is not visible...
		 do{
			 playerTurn += 1;
			 if(playerTurn >= playerLabels.length){
					playerTurn = 0;
			 }
		 } while(!playerLabels[playerTurn].isVisible());	 
		
		// would this work for all instances?
		if(hasWon){
			if(playerTurn == initWinIndex){
				if(isTied){
					solveTie();
				} else{
					EndScene end = new EndScene(winner);
					end.scene(scene.getStage());
				}
			}
		} 
		
		current = players[playerTurn];
		playerLabel = playerLabels[playerTurn];
		playerLabel.setId("playerTurn");
		roll();
	}
	
	private void checkWin(){
		if(!hasWon){
			if(current.getBrains() >= winningBrains){
				winningBrains = current.getBrains();
				winner = current;
				tiedPlayers.add(current);
				initWinIndex = current.getID();
				hasWon = true;
				scene.getWinText().setText(winner.getName() + " has won! Everyone gets one more chance to win.\nYou need " + 
						(winningBrains+1) + " Brains to win or " + winningBrains + " Brains to tie");
			}
		} else{
			if(current.getBrains() > winningBrains){
				hasWon = true;
				isTied = false;
				winner = current;
				tiedPlayers.clear(); tiedPlayers.add(current); // just in case more tiedPlayers occur
				winningBrains = current.getBrains();	
				scene.getWinText().setText(winner.getName() + " is winning!\nYou need " + 
						(winningBrains+1) + " Brains to win or" + winningBrains + " Brains to tie");
			} else if(current.getBrains() == winningBrains && initBrains != winningBrains){  
				isTied = true;
				tiedPlayers.add(current);
				scene.getWinText().setText(current.getName() + " is tied with " + winner.getName()
						+ "!\nYou need " + (winningBrains+1) + " Brains to win or " + winningBrains + " Brains to tie");
			}
		}
	}
	
	private void solveTie(){
		isTied = false;
		hasWon = false;
		initWinIndex = tiedPlayers.get(0).getID();;
		initBrains = winningBrains;
		
		String tieText = "";
		tieText += tiedPlayers.get(0).getName();
		for(int i = 1; i < tiedPlayers.size()-1; i++){
			tieText += ", " + tiedPlayers.get(i).getName();
		}
		tieText += " and " + tiedPlayers.get(tiedPlayers.size()-1).getName() + " ";
		tieText += "are currently tied. \nThey will get another round to see who will win.\nYou need " + 
					(winningBrains+1) + " Brains to win or " + winningBrains + " Brains to tie";
		scene.getWinText().setText(tieText);
		scene.getStage().sizeToScene();
		
		Label[] playerLabels = scene.getPlayerLabels();
		for(int i = 0; i < playerLabels.length; i++){
			playerLabels[i].setVisible(false);	
		}
		for(int i = 0; i < tiedPlayers.size(); i++){
			int index = tiedPlayers.get(i).getID();
			playerLabels[index].setVisible(true);
		}
		tiedPlayers.clear();
	}
	
	public void roll(){
		if(shotgunBlasts >= 3){
			return;
		}
		
		ImageView[] dieImgs = scene.getDieImgs();
		
		for(int i = 0; i < dies.length; i++){
			if(dies[i] == null || dies[i].getRoll() != RollValue.Footprint){
				dies[i] = cup.getDie(); 
			}
			dies[i].roll();
			adjustRoll(dies[i], dieImgs[i]);
		}
		
		Label rollLabel = scene.getRollLabel();
		if(shotgunBlasts >= 3){
			rollLabel.setText("The survivors have fought back and escaped");
		} else {
			rollLabel.setText(current.getName() + " has " + survivorsCornered +" Survivors Cornered and " + shotgunBlasts + 
					" Shotgun Blasts");
		}
	}
	
	private void adjustRoll(Die d, ImageView i){
		switch(d.getRoll()){
			case Survivor_Cornered:
				survivorsCornered++;
				switch(d.getDiceType()){
					case Green:
						i.setImage(textures.getGreenSurvivorImg());
						break;
					case Yellow:
						i.setImage(textures.getYellowSurvivorImg());
						break;
					case Red:
						i.setImage(textures.getRedSurvivorImg());
						break;
				}
				break;
			case Shotgun_Blast:
				shotgunBlasts++;
				switch(d.getDiceType()){
					case Green:
						i.setImage(textures.getGreenShotgunImg());
						break;
					case Yellow:
						i.setImage(textures.getYellowShotgunImg());
						break;
					case Red:
						i.setImage(textures.getRedShotgunImg());
						break;
				}
				break;
			case Footprint:
				// do nothing
				switch(d.getDiceType()){
					case Green:
						i.setImage(textures.getGreenFootprintImg());
						break;
					case Yellow:
						i.setImage(textures.getYellowFootprintImg());
						break;
					case Red:
						i.setImage(textures.getRedFootprintImg());
						break;
				}
				break;
		}
	}
	
	public Player[] getPlayers(){
		return players;
	}
}
