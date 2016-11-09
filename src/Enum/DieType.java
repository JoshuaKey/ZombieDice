package Enum;

import java.util.Random;

public enum DieType {
	Green, Yellow, Red;
	
	public RollValue[] getRollValues(){
		RollValue[] rolls = null;
		switch(this){
		case Red:
			rolls = new RollValue[]{
					RollValue.Survivor_Cornered, RollValue.Footprint, RollValue.Footprint, 
					RollValue.Shotgun_Blast, RollValue.Shotgun_Blast, RollValue.Shotgun_Blast
			};
			break;
		case Green:
			rolls = new RollValue[]{
					RollValue.Survivor_Cornered, RollValue.Survivor_Cornered, RollValue.Survivor_Cornered, 
					RollValue.Footprint, RollValue.Footprint, RollValue.Shotgun_Blast
			};
			break;
		case Yellow:
			rolls =  new RollValue[]{
					RollValue.Survivor_Cornered, RollValue.Survivor_Cornered, RollValue.Footprint, 
					RollValue.Footprint, RollValue.Shotgun_Blast, RollValue.Shotgun_Blast
			};
			break;
		}
		Random rand = new Random();
		for(int i = 0; i < rolls.length; i++){
			int pos = rand.nextInt(rolls.length);
			RollValue temp = rolls[i];
			rolls[i] = rolls[pos];
			rolls[pos] = temp;
		}
		return rolls;
	}
}

// return getTitle() + " (" + getYear() + ") - " + getFilmGenre();
