package Players;

public class Player {

	private String name;
	private int brainsAte, id;
	
	public Player(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	public void increaseBrains(int value){
		brainsAte += value;
	}
	public String getName(){
		return name;
	}
	public int getBrains(){
		return brainsAte;
	}
	public int getID(){
		return id;
	}
}
