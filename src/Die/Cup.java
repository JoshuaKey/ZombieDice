package Die;

import java.util.Random;

import Enum.DieType;
import Enum.RollValue;

public class Cup {
	private Die[] dies;
	private boolean[] usedDies;
	private Random rand;

	public Cup(int diff){
		rand = new Random();
		int greenDice = 0, yellowDice = 0;
		switch(diff){
		case 0:
			dies = new Die[20];
			greenDice = 10;
			yellowDice = 8;
			break;
		case 1:
			dies = new Die[13];
			greenDice = 6;
			yellowDice = 4;
			break;
		case 2:
			dies = new Die[10];
			greenDice = 2;
			yellowDice = 3;
			break;
		}
		usedDies = new boolean[dies.length];
		
		for(int i = 0; i < dies.length; i++){
			if(i >= yellowDice+greenDice){
				dies[i] = new Die(DieType.Red);
			} else if(i >= greenDice){
				dies[i] = new Die(DieType.Yellow);
			} else {
				dies[i] = new Die(DieType.Green);
			}
		}
	}
	
	// why can't i just use an array list???????
	// or map.....
	public Die getDie(){
		int die = rand.nextInt(dies.length); 
		int temp = die;
		
		while(usedDies[die]){
			die++;
			if(die == dies.length) { die = 0; }
			if(die == temp){ placeDiceBackIntoCup(); }		
		}
		usedDies[die] = true;
		
		return dies[die];
	}
		
	private void placeDiceBackIntoCup(){
		System.out.println("Dice placed back into the cup.");
		for(int i = 0; i < dies.length; i++){
			if(dies[i].getRoll() == RollValue.Survivor_Cornered){
				usedDies[i] = false;
			}
		}
	}
	
	public void reset(){
		usedDies = new boolean[dies.length];
	}
}
