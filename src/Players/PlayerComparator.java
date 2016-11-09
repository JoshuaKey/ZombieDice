package Players;

import java.util.Comparator;

public class PlayerComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Player p1 = (Player) o1;
		Player p2 = (Player) o2;
		if(p1.getID() < p2.getID()){
			return -1;
		} else if(p1.getID() > p2.getID()){
			return 1;
		}
		
		return 0;
	}
	
}
