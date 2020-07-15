import java.util.*;
import java.util.Hashtable;
import java.util.Enumeration;
class TeleportEvent extends Event{
private Dungeon d;
private String g;

    TeleportEvent(String g){
	this.g = g;
	this.d = GameState.instance().getDungeon();
    }
    
    String callEvent(){	
	GameState state = GameState.instance();
	ArrayList<String> ListOfRooms = GameState.instance().getDungeon().getRoomList();
	//for(int i = 0; i < ListOfRooms.size(); i++){
	//	ListOfRooms.add(roomNames.nextElement().getRoomName().getTitle());	
	//}
	//Collections.shuffle(ListOfRooms);
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
		Room r = enu.nextElement();
		c.put(intArray[i], r);
		i++;
	}
	Random rand = new Random();
	int index = rand.nextInt(c.size() + 0) - 0;
	//Room randomRoom = state.getDungeon().getRoom(ListOfRooms.get(index));
	state.setAdventurersCurrentRoom(c.get(index));
	return "";
    }
}

