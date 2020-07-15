import java.util.*;
class TeleportEvent extends Event{
private Dungeon d;

    TeleportEvent(){
	this.d = GameState.instance().getDungeon();
    }
    
    String callEvent(){
	GameState state = GameState.instance();
	Hashtable<String,Room> b = new Hashtable<String,Room>();
	b = d.getRoomsList();
	Enumeration enu = b.keys();
	Hashtable<Integer,Room> c = new Hashtable<Integer,Room>();
	Integer[] intArray;
	for(int i = 0; i < intArray.length; i++){
		intArray[i] = i;
	}
	List<Integer> intList = Arrays.asList(intArray);
	Collections.shuffle(intList);
	intList.toArray(intArray);
	int i = 0;
	while(enu.hasMoreElements()){
		c.put(intArray[i], enu.nextElement());
		i++;
	}
	Random rand = new Random();
	int index = rand.nextInt(c.size() + 0) - 0;
	state.setAdventurersCurrentRoom(c.get(index));
	return "";
    }
}

