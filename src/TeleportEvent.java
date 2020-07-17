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
//	ArrayList<String> ListOfRooms = GameState.instance().getDungeon().getRoomList();
//	Hashtable<String,Room> b;
//	b = d.getRoomsList();
//	Enumeration enu = b.keys();
//	Hashtable<Integer,Room> c = new Hashtable<Integer,Room>();
//	int[] intArray = new int[c.size() ];
//	for(int i = 0; i < intArray.length; i++){
//		intArray[i] = i;
//	}
//	List<Integer> intList = Arrays.asList(intArray);
//	Collections.shuffle(intList);
//	intList.toArray(intArray);
//	int i = 0;
//	Set<String> keys = b.keySet();
//	for(String key: keys){
//		c.put(intArray[i], b.get(key));
//		i++;
//	}
//	Random rand = new Random();
//	int index = rand.nextInt(c.size());
	try{
		if(GameState.instance().getTest()==true){
		System.out.println("Entering try block");}
	String roomName = "WinterFell";
		if(GameState.instance().getTest()==true){
		System.out.println("Room Name:"+roomName);}
	Room newRoom = state.getDungeon().getRoom(roomName);
		if(GameState.instance().getTest()==true){
		System.out.println("New Room:"+newRoom.getTitle());}
	
	state.setAdventurersCurrentRoom(newRoom);
	return "*POOF!* You have been teleported to the "+roomName+" room.";
	}
	catch (NoRoomException nre){
	return "Um... where?";
    }
}

}

