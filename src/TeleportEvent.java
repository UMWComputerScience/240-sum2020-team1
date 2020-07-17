import java.util.*;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Random;
class TeleportEvent extends Event{
private Dungeon d;
private String g;

    TeleportEvent(String g){
	this.g = g;
	this.d = GameState.instance().getDungeon();
    }
    
    String callEvent(){	
	Random rand = new Random();
	GameState state = GameState.instance();
	try{
		if(GameState.instance().getTest()==true){
		System.out.println("Entering try block");}
	String roomName = "Winterfell";
		if(GameState.instance().getTest()==true){
		System.out.println("Room Name:"+roomName);}
	
	Room newRooms = state.getDungeon().getRoom(roomName);
		if(GameState.instance().getTest()==true){
		System.out.println("New Room:"+newRooms.getTitle());}
	String[] roomList = GameState.instance().getDungeon().getRoomList();
	String newRoomName = roomList[rand.nextInt(roomList.length)];
	Room newRoom = GameState.instance().getDungeon().getRoom(newRoomName);
	state.setAdventurersCurrentRoom(newRoom);
	return "*POOF!* You have been teleported to the "+roomName+" room.";
	}
	catch (NoRoomException nre){
	return "Um... where?";
    }
}

}

