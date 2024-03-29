import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Enumeration;
/**
* A dungeon is a grouping of {@link room}s and their associated {@link exit}s. 
* A dungeon may also have Non Player Characters, weapons, items and lockable exits.
*/
public class Dungeon {
	/*
	 * Thrown during hydration when the program cannot read a file correctly
	 * due to a format error.*/
    /*public static class IllegalDungeonFormatException extends Exception {
        public IllegalDungeonFormatException(String e) {
            super(e);
        }*/
    // Variables relating to both dungeon file and game state storage.
    public static String TOP_LEVEL_DELIM = "===";
    public static String SECOND_LEVEL_DELIM = "---";

    // Variables relating to dungeon file (.zork) storage.
    public static String ROOMS_MARKER = "Rooms:";
    public static String EXITS_MARKER = "Exits:";
    public static String ITEMS_MARKER = "Items:";
    public static String NPC_MARKER = "NonPlayerCharacters:";
    public static String GUARD_MARKER = "Guards:";
    // Variables relating to game state (.sav) storage.
    static String FILENAME_LEADER = "Dungeon file: ";
    static String ROOM_STATES_MARKER = "Room states:";

    private String name;
    private Room entry;
    private Hashtable<String,Room> rooms;
    private Hashtable<String,Item> items;
    private String filename;
    private ArrayList<String> listOfRooms;
    private Hashtable<String,NonPlayerCharacter> npcs;
    private Hashtable<String,Guard> guards;
    private Hashtable<String,Item> craftableItems;

    /**
     * Creates a Dungeon object and designates a room in that object as the 'entry' room.
     * */
    Dungeon(String name, Room entry) {
        init();
        this.filename = null;    // null indicates not hydrated from file.
        this.name = name;
        this.entry = entry;
 //       Hashtable<String,Room> rooms = new Hashtable<String,Room>();
//	Hashtable<String,Item> craftableItems = new Hashtable<String, Item>();
//	Hashtable<String,NonPlayerCharacter> npcs = new Hashtable<String,NonPlayerCharacter>();
    }
//	/** Exception to be thrown if an invalid or incompatible .zork file is detected
//	* when loading a save game or starting a new game.
//	*/
//  class IllegalDungeonFormatException extends Exception {
//      public void IIllegalDungeonFormatException(String e) {
//            super(e);
//        }
//    }

    /**
     * Reads from the .zork filename passed, and creates a Dungeon 
     * based on the information found in the file.
     * @throws IllegalDungeonFormatException if an incompatible .zork file is found.
     * @throws FileNotFoundException if the .zork file does not exist.
     */
    public Dungeon(String filename) throws FileNotFoundException, 
        IllegalDungeonFormatException, NoRoomException {

        this(filename, true);
    }

    /**Creates a dungeon by reading the contents of a supplied .zork file, this will include items and NPCs/Guards
     * and place them in the specified rooms.
     *
     * @throws IllegalDungeonFormatException if an incompatible .zork file is found.
     * @throws FileNotFoundException if the .zork file does not exist.
     */
    public Dungeon(String filename, boolean initState) 
        throws FileNotFoundException, IllegalDungeonFormatException, NoRoomException {
	ArrayList<String> listOfRooms = new ArrayList<String>();
        init();
        this.filename = filename;

        Scanner s = new Scanner(new FileReader(filename));
        name = s.nextLine();

        s.nextLine();   // Throw away version indicator.

        // Throw away delimiter.
        if (!s.nextLine().equals(TOP_LEVEL_DELIM)) {
            throw new IllegalDungeonFormatException("No '" +
                TOP_LEVEL_DELIM + "' after version indicator.");
        }

        // Throw away Items starter.
        if (!s.nextLine().equals(ITEMS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                ITEMS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate items.
            while (true) {
                add(new Item(s));
            }
        } catch (NoItemException e) {  /* end of items */ }
	if(!s.nextLine().equals(NPC_MARKER)) {
		throw new IllegalDungeonFormatException("No '"+NPC_MARKER+"' line where expected.");}
	try{
		while(true){
			NonPlayerCharacter n = new NonPlayerCharacter(s);
			if(GameState.instance().getTest()==true){
				System.out.println("NPC:"+n.getName()+" Created");}
			add(n);
			if(GameState.instance().getTest()==true){
				System.out.println("Added NPC");}
			}
	} catch (NoNonPlayerException n){}
//	if(!s.nextLine().equals(GUARD_MARKER)){
//		throw new IllegalDungeonFormatException("No '"+GUARD_MARKER+"' line where expected.");}
//	try {
//		while(true){
//			Guard g = new Guard(s);
//			if(GameState.instance().getTest()==true){
//				System.out.println("Guard: "+g.getName()+" Created");}
//			add(g);
//			if(GameState.instance().getTest()==true){
//				System.out.println("Added Guard");}
//		}
//	} catch (Exception n){}
        // Throw away Rooms starter.
        if (!s.nextLine().equals(ROOMS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                ROOMS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate and add first room (the entry).
            entry = new Room(s, this, initState);
            add(entry);
	    listOfRooms.add(entry.getTitle());

            // Instantiate and add other rooms.
            while (true) {
                add(new Room(s, this, initState));
		
            }
        } catch (NoRoomException e) {  /* end of rooms */ }

        // Throw away Exits starter.
        if (!s.nextLine().equals(EXITS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                EXITS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate exits.
            while (true) {
                Exit exit = new Exit(s, this);
            }
        } catch (NoExitException e) {  /* end of exits */ }

        s.close();
    }
    
    // Common object initialization tasks, regardless of which constructor
    // is used.
    /**Initialisation method that creates storage for rooms, items, and craftable items for the dungeon. This ensures that the dungeon always has storage for these items.
     */
    private void init() {
        rooms = new Hashtable<String,Room>();
        items = new Hashtable<String,Item>();
	craftableItems = new Hashtable<String, Item>();
	npcs = new Hashtable<String,NonPlayerCharacter>();
	guards = new Hashtable<String,Guard>();
    }

    /**
     * Stores the current state of this dungeon to a .sav file.
     */
    void storeState(PrintWriter w) throws IOException {
        w.println(FILENAME_LEADER + getFilename());
        w.println(ROOM_STATES_MARKER);
        for (Room room : rooms.values()) {
            room.storeState(w);
        }
        w.println(TOP_LEVEL_DELIM);
    }

    /**
     * Restores the state of a dungeon through a read .sav file.
     * @throws IllegalSaveFormatException if the file has a format error.
     */
    void restoreState(Scanner s) throws  NoRoomException {

        // Note: the filename has already been read at this point.    
//        if (!s.nextLine().equals(ROOM_STATES_MARKER)) {
//            throw new IllegalSaveFormatException();
//	System.out.println("No '" +ROOM_STATES_MARKER + "' after dungeon filename in save file.");
//        }

        try{
            if (!s.nextLine().equals(ROOM_STATES_MARKER)) {
                throw new IllegalSaveFormatException();
	    }
	}
	catch(IllegalSaveFormatException e){
	    System.out.println("No'" + ROOM_STATES_MARKER + "'after dungeon filename in save file.");
	}

        String roomName = s.nextLine();
	try{
	        while (!roomName.equals(TOP_LEVEL_DELIM)){
			try{
	       	    	getRoom(roomName.substring(0,roomName.length()-1)).restoreState(s, this);
			//System.out.println(roomName);
 	            roomName = s.nextLine();
			}
			catch(IllegalSaveFormatException e){}
		}
	}
	catch(NoRoomException r){}
    }
/**Returns the entry room of a dungeon.*/
    public Room getEntry() { return entry; }
/**Returns the name of the Dungeon.*/
    public String getName() { return name; }
/**Returns the name of the .zork file.*/
    public String getFilename() { return filename; }
/**Adds a room to the dungeon.*/
    public void add(Room room) { rooms.put(room.getTitle(),room); }
/**Adds an item to the dungon inventory.*/
    public void add(Item item) { items.put(item.getPrimaryName(),item); }
/**Adds an NPC to the Dungeon Character list*/
    public void add(NonPlayerCharacter n){ npcs.put(n.getName(),n);}
/**Adds a guard to the guards list */
    public void add(Guard g){ guards.put(g.getName(),g);}

    /**
     * Returns a room whose name matches the supplied room title.
     * @throws NoRoomException if no room is found by the given name.
     */
    public Room getRoom(String roomTitle) throws NoRoomException {
        return rooms.get(roomTitle);
    }
    /**
     * Returns an item whose name matches the supplied item name.
     * @throws NoItemException if no item is found by the given name.
     */
    public Item getItem(String primaryItemName) throws NoItemException {
       try{ 
           if (items.get(primaryItemName) == null) {
               throw new NoItemException();
           }
       }
       catch(NoItemException e){
       }
       return items.get(primaryItemName);
    }
/**returns a NonPlayerCharacter from the Dungeons hashtable. */
    public NonPlayerCharacter getNPC(String name) throws NoNonPlayerException {
	try{
		if(npcs.get(name) == null){
			throw new NoNonPlayerException();
		}
	}
	catch(NoNonPlayerException n){
	}
	return npcs.get(name);
    }
    public Hashtable<String,Room> getRoomsList(){
	    return rooms;
    }
    public String getNpcList(){
	String npcList ="";
	if(GameState.instance().getTest()==true){
		System.out.println("Dungeon's getNpcList() called.");
	}
	Enumeration npcKeysList =this.npcs.keys();
	if(GameState.instance().getTest()==true){
		System.out.println("Enumeration list created");
	}
	while(npcKeysList.hasMoreElements()){
	if(GameState.instance().getTest()==true){
		System.out.println("Current npcList: "+npcKeysList);
	}
	npcList = npcList+":"+npcKeysList.nextElement();	
	}
	return npcList;
	}
    public String[] getRoomList(){
	String[] listOfRooms = new String[getRoomsList().size()];
	ArrayList<String> tempRoomNames = new ArrayList<String>();
	Enumeration<String> roomKeys = getRoomsList().keys();
	while(roomKeys.hasMoreElements()){
		String roomName = roomKeys.nextElement();
		if(GameState.instance().getTest()==true){
		System.out.println("current room:"+roomName);}
		tempRoomNames.add(roomName);
	}
	for(int i = 0;i<tempRoomNames.size();i++){
		listOfRooms[i]=tempRoomNames.get(i);
	}

	return listOfRooms;
	}
    public Hashtable<String,Item> getItemList(){
	    return items;
    }
    /**
     * Takes the name of an item that the player has indicated they want to craft.
     * Chechs the player's inventory to see if the player has the two items.
     * Returns an ArrayList of two items used to craft an item.
     * @throws NoItemException if no item is found by the given name.
     */
    public ArrayList<Item> checkRequirements(String requirements) throws NoItemException{
	ArrayList<Item> tempList = new ArrayList<Item>();
	return tempList;
	}
}


