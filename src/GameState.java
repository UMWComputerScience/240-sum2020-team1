
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

    private static GameState theInstance;
    private Dungeon dungeon;
    private ArrayList<Item> inventory;
    private Room adventurersCurrentRoom;

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
    }
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
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

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
        if (s.hasNext()) {
            String inventoryList = s.nextLine().substring(
                INVENTORY_LEADER.length());
            String[] inventoryItems = inventoryList.split(",");
            for (String itemName : inventoryItems) {
                try {
                    addToInventory(dungeon.getItem(itemName));
                } catch (Item.NoItemException e) {
                    throw new IllegalSaveFormatException("No such item '" +
                        itemName + "'");
                }
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
    void addToInventory(Item item) /* throws TooHeavyException */ {
        inventory.add(item);
    }
	/**
	 * Removes an Item from the player's inventory.*/
    void removeFromInventory(Item item) {
        inventory.remove(item);
    }
	/**Returns an item from the current room or inventory.
	 * Note that the player's inventory is checked before the
	 * room's inventory.
	 * Throws NoItemException if no item goes by that name.*/
    Item getItemInVicinityNamed(String name) throws Item.NoItemException {

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

        throw new Item.NoItemException();
    }
	/**
	 * Returns an Item from the player's inventory.
	 * Note that the item's primar name is checked before it's alias names.
	 * If no item is found by it's name, throws Item.NoItemException*/
    Item getItemFromInventoryNamed(String name) throws Item.NoItemException {

        for (Item item : inventory) {
            if (item.goesBy(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
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

}
