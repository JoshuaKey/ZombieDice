package Die;

import java.util.Random;

import Enum.RollValue;
import Enum.DieType;

public class Die {
	
	private Random rand;
	private RollValue roll;
	private final RollValue[] rolls;
	private final DieType diceType;
	
	public Die(DieType value){
		diceType = value;
		rolls = value.getRollValues();
		rand = new Random(); // implement a way for new seed or good random function...
	}
	
	public void roll(){
		// suggest i could get the roll values from the dieType instead of storing it
		roll = rolls[rand.nextInt(rolls.length)];
	}
	
	public DieType getDiceType(){
		return diceType;
	}
	public RollValue getRoll(){
		return roll;
	}
	
	@Override
	public String toString(){
		return getDiceType() + " die rolled a " + getRoll();
	}
}
