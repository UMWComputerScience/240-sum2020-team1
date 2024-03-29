
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Stores information about the current state of the dungeon.
 * Includes the player's inventory, the current room, the dungeon,
 * and the total weight of the items in player's inventory.
 * */
public class GameState {

	/**
	 * Extends Exception and is thrown during hydration if the save file 
	 * does not conform to what the program checks for.
	 * */
    public static class IllegalSaveFormatException extends Exception {
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }

    static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Zork III save data";

    static String ADVENTURER_MARKER = "Adventurer:";
    static String CURRENT_ROOM_LEADER = "Current room: ";
    static String INVENTORY_LEADER = "Inventory: ";
    static boolean test;
    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private Room adventurersCurrentRoom;
    private int health = 12;
    private int score = 0;
    private int hunger = 12;
    private int hungerCount=0;
    private int hungerInc = 2;
    /**
     * Singleton that instantiates one instance of GameState theInstance.*/
    static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
        }
        return theInstance;
    }
	/**
	 * Creates the inventory storage.*/
    private GameState() {
        inventory = new ArrayList<Item>();
	test = false;
    }
/**returns the value of the test value. Used for determing if a value should be shown on the console cor debugging purposes.
*/
    public boolean getTest(){ return test;} 
	/** turns on or off debugging print statements allowing the coder to check output of various elements during operation
*/
    public void setTest(boolean b){ this.test = b;}
	/**
	 * Returns the total weight of the items in the player's inventory.*/
    int getAdventurersCurrentWeight() {
        int total = 0;
        for (Item item : inventory) {
            total += item.getWeight();
        }
        return total;
    }
	/**
	 * Hydrate a previous save from a .sav file. 
	 * Checks for format and if there is an error,
	 * If the format of the supplied file does not match the
	 * intended format, throws IllegalSaveFormatException.
	 * If a file does not exist in the intended location, throws FileNotFoundException.
	 * If the supplied file does not end in '.zork', throws 
	 * Dungeon.IllegalDungeonFormatException.*/
    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, IllegalDungeonFormatException, NoRoomException, maxLoadException{

        Scanner s = new Scanner(new FileReader(filename));

        if (!s.nextLine().equals(SAVE_FILE_VERSION)) {
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
        Dungeon.FILENAME_LEADER.length()), false);
        dungeon.restoreState(s);

        s.nextLine();  // Throw away "Adventurer:".
        String currentRoomLine = s.nextLine();
        adventurersCurrentRoom = dungeon.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length()));
        while (s.hasNext()) {
		String nextLineCheck = s.nextLine();
		if( nextLineCheck.contains(INVENTORY_LEADER)){
			if(GameState.instance().getTest()==true){
			System.out.println(INVENTORY_LEADER.length());
			}
//            String inventoryList = s.nextLine().substring(INVENTORY_LEADER.length());
		String inventoryList = nextLineCheck.substring(INVENTORY_LEADER.length());
	            String[] inventoryItems = inventoryList.split(",");
        	    if(GameState.instance().getTest()==true){
		     System.out.println("entering inventory loop");
			}	    
		for (String itemName : inventoryItems) {
                	try {
	                    addToInventory(dungeon.getItem(itemName));
        	        } catch (NoItemException e) {
                	    throw new IllegalSaveFormatException("No such item '" +
                        	itemName + "'");
                		}
        	    	}
 	       	}
		if( nextLineCheck.contains("health:")){
		String[] healthLine = nextLineCheck.split(":");
		if(GameState.instance().getTest()==true){
		System.out.println("HealthLine[0]: - "+healthLine[0]);
		System.out.println("HealthLine[1]: - "+healthLine[1]);
		}
		int healthValue = Integer.parseInt(healthLine[1]);
		GameState.instance().setHealth(healthValue);
		}
		if(nextLineCheck.contains("hunger:")){
			String [] hungerLine = nextLineCheck.split(":");
			int hungerValue = Integer.parseInt(hungerLine[1]);
			GameState.instance().setHunger(hungerValue);
		}
		if(nextLineCheck.contains("hungerCount:")){
			String [] hungerCountLine = nextLineCheck.split(":");
			int hungerCountValue = Integer.parseInt(hungerCountLine[1]);
			GameState.instance().setHungerCount(hungerCountValue);
		}
		if( nextLineCheck.contains("score: ")){
			String[] scoreLine = nextLineCheck.split(": ");
			int scoreValue = Integer.parseInt(scoreLine[1]);
			if(GameState.instance().getTest()==true){
			System.out.println("Scoreline[0]: "+scoreLine[0]);
			System.out.println("ScoreLine[1]: "+scoreLine[1]);
			}
			GameState.instance().setScore(scoreValue);
		}
	}    
}
	/**
	 * Writes the current state of a game to a .sav file.
	 * Throws IOException if file is unable to be written.*/
    void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(ADVENTURER_MARKER);
        w.println(CURRENT_ROOM_LEADER + adventurersCurrentRoom.getTitle());
        if (inventory.size() > 0) {
            w.print(INVENTORY_LEADER);
            for (int i=0; i<inventory.size()-1; i++) {
                w.print(inventory.get(i).getPrimaryName() + ",");
            }
            w.println(inventory.get(inventory.size()-1).getPrimaryName());
        }
	w.println("health:"+GameState.instance().getHealth());
	w.println("hunger:"+GameState.instance().getHunger());
	w.println("hungerCount:"+GameState.instance().getHungerCount());
	w.println("score: "+GameState.instance().getScore());
        w.close();
    }
	/**
	 * Ensures that the player's current room is the entry room at the beginning of the game.*/
    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        adventurersCurrentRoom = dungeon.getEntry();
    }
	/**
	 * Returns a list of the names of each item in the player's inventory.*/
    ArrayList<String> getInventoryNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Item item : inventory) {
            names.add(item.getPrimaryName());
        }
        return names;
    }
	/**
	 * Adds an Item to the player's inventory.*/
    void addToInventory(Item item) throws maxLoadException  /* throws TooHeavyException */ {
	if(item.getWeight()+getAdventurersCurrentWeight()>GameState.instance().getStrength()){
		throw new maxLoadException();}
	else{
	      inventory.add(item);
	}
    }
	/**
	 * Removes an Item from the player's inventory.*/
    void removeFromInventory(Item item) throws NoItemException{
        inventory.remove(item);
    }
	/**Returns an item from the current room or inventory.
	 * Note that the player's inventory is checked before the
	 * room's inventory.
	 * Throws NoItemException if no item goes by that name.*/
    Item getItemInVicinityNamed(String name) throws NoItemException {

        // First, check inventory.
        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        // Next, check room contents.
        for (Item item : adventurersCurrentRoom.getContents()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        throw new NoItemException();
    }
	/**
	 * Returns an Item from the player's inventory.
	 * Note that the item's primar name is checked before it's alias names.
	 * If no item is found by it's name, throws Item.NoItemException*/
    Item getItemFromInventoryNamed(String name) throws NoItemException {

        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }
        throw new NoItemException();
    }
	/**
	 * Returns the room that the adventurer is currently in.*/
    Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }
	/**
	 * Sets the adventurers current room to the present room.*/
    void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }
	/**
	 * Returns dungeon*/
    Dungeon getDungeon() {
        return dungeon;
    }
    void wound(int w){
	this.health = this.health - w;
	if(this.health > 12){
		this.health = 12;
		}
	}
    int getHunger(){
		return this.hunger;
	}
    int getHungerCount(){
		return this.hungerCount;
	}

    void setHunger(int num){
		this.hunger = num;
	}
    void setHungerCount(int num){this.hungerCount = num;}

    int getHealth(){
	    return this.health;
	}
    void setHealth(int num){
	    this.health = num;
    }
    int getScore() {
		return this.score;
	}
    void setScore(int i){
		this.score = i;
	}
    void increaseScore(int value){
	    instance().score = instance().score + value;
    }
    ArrayList<Item> getInventory(){
		return this.inventory;
	}

    void returnToStart(){
	System.out.println("********************************************************************");
	System.out.println(" You kinda stopped living.");
	System.out.println(" Through some act of surpreme unviersal irony, the magical fairy,\n Darth Davies, has taken pitty on you.");
	System.out.println(" With a wave of his magic wand, he has returned your soul to your\n body and sent you to the start of the dungeon.\n Try not to do something stupid this time.");
//	CommandFactory.instance().parse("drop all");
	GameState.instance().setAdventurersCurrentRoom(GameState.instance().getDungeon().getEntry());
	GameState.instance().setHealth(12);
	System.out.println("********************************************************************");
	System.out.println(GameState.instance().getAdventurersCurrentRoom().describe(true));
	
    }  
/** changes the character HungerCount increments of hunterInc */
    void hungerCountChange(){
	if(hungerCount<hungerInc){
		hungerCount+=1;
		}
	else if(hungerCount==hungerInc){
		hunger = 0;
		}
	}

/**resets hunger to 0 */
    void resetHunger(){hunger = 0;}
/**determines the player's carrying capacity by taking the score, dividing it by 5 and adding 40 to that.*/
	int getStrength(){
		int testValue = (int)(score/5)+40;
		return testValue;
	}
	
/**checks if an item is in the players inventory*/
	boolean checkInv(String itemName){
		boolean check = false;
		
		try{Item checked = GameState.instance().getItemFromInventoryNamed(itemName);
		     check= true;}
		catch(NoItemException n){
		}
	return check;
	}
/** returns hunger*/
	int getCheck(){return hunger;}
/**returns hungerCount*/
	int hungerCountCheck(){return hungerCount;}
/**returns hunger incriment*/
	int hungerIncCheck(){return hungerInc;}

/** checks if the hungerCount is 0, returns true if it is. */
boolean checkHunger(){
	if(hunger != 0){return false;}
	else{return true;}}

void checkHungerCount(){
	if(hungerCount<hungerInc){
		hungerCount += 1;
	}
	else{
		if(checkHunger()==false){
			hunger = hunger-1;
			hungerCount = 0;
			}
		else{
			if(hungerCount==hungerInc){
				wound(1);
			}
			hungerCount = 0;
			}
		}
	}
}
