
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * A room is a location that the player can navigate to and from using its exits. Rooms can
 * also hold items and NPCs/Guards.
 */
public class Room {

//    class NoRoomException extends Exception {}

    static String CONTENTS_STARTER = "Contents: ";

    private String title;
    private String desc;
    private boolean beenHere;
    private ArrayList<Item> contents;
    private ArrayList<Exit> exits;
    /**Constructor that creates a single Room with a provided title.
    **/
    Room(String title) {
        init();
        this.title = title;
    }
    /**Constructor that reads the contents of a file to create Rooms and add them to a supplied Dungeon.
     * @throws NoRoomException The file does not have any Rooms available to be read to a Dungeon.
     * @throws IllegalDungeonFormatException The file does not end in .zork.
    **/
    Room(Scanner s, Dungeon d) throws NoRoomException,
        IllegalDungeonFormatException {

        this(s, d, true);
    }

    /** Given a Scanner object positioned at the beginning of a "room" file
        entry, read the contents of that entry and create a Room object based on that information.
        @param d The containing {@link Dungeon} object, necessary to 
        retrieve {@link Item} objects.
        @param initState Ensures that the file is of the correct format.
        @throws NoRoomException The reader object is not positioned at the
        start of a room entry. A side effect of this is the reader's cursor
        is now positioned one line past where it was.
        @throws IllegalDungeonFormatException A structural problem is found with the contents of the Dungeon file.
     */
    Room(Scanner s, Dungeon d, boolean initState) throws NoRoomException,
	IllegalDungeonFormatException {

        init();
        title = s.nextLine();
        desc = "";
        if (title.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoRoomException("No more rooms in file" );
        }
        
        String lineOfDesc = s.nextLine();
        while (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM) &&
               !lineOfDesc.equals(Dungeon.TOP_LEVEL_DELIM)) {

            if (lineOfDesc.startsWith(CONTENTS_STARTER)) {
                String itemsList = lineOfDesc.substring(CONTENTS_STARTER.length());
                String[] itemNames = itemsList.split(",");
                for (String itemName : itemNames) {
                    try {
                        if (initState) {
                            add(d.getItem(itemName));
                        }
                    } catch (NoItemException e) {
                        throw new IllegalDungeonFormatException(
                            "No such item '" + itemName + "'");
                    }
                }
            } else {
                desc += lineOfDesc + "\n";
            }
            lineOfDesc = s.nextLine();
        }

        // throw away delimiter
        if (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after room.");
        }
    }

    /**
     * Common object initialization tasks that include instantiating storage for items, exits, and the boolean value for whether or not the player had been in a Room (which, at Dungeon creation, is false in every case.)
     */
    private void init() {
        contents = new ArrayList<Item>();
        exits = new ArrayList<Exit>();
        beenHere = false;
    }
    /**Returns the title of the Room.
    **/
    String getTitle() { return title; }
    /**Sets the description of a Room to the provided message.
    **/
    void setDesc(String desc) { this.desc = desc; }

    /**
     * Stores the current state of this room to a .sav file.
     * @throws IOException The .sav file cannot be found.
     */
    void storeState(PrintWriter w) throws IOException {
        w.println(title + ":");
        w.println("beenHere=" + beenHere);
        if (contents.size() > 0) {
            w.print(CONTENTS_STARTER);
            for (int i=0; i<contents.size()-1; i++) {
                w.print(contents.get(i).getPrimaryName() + ",");
            }
            w.println(contents.get(contents.size()-1).getPrimaryName());
        }
        w.println(Dungeon.SECOND_LEVEL_DELIM);
    }
	/**
	 * Restores the state of this room through the passed scanner by reading a .sav file.
	 */
    void restoreState(Scanner s, Dungeon d) throws 
        IllegalSaveFormatException {
	String line = s.nextLine();
	try{
        if (!line.startsWith("beenHere")) {
            throw new IllegalSaveFormatException();
        }
	}
	catch(IllegalSaveFormatException e){
		System.out.println("No beenHere.");
	}
        beenHere = Boolean.valueOf(line.substring(line.indexOf("=")+1));

        line = s.nextLine();
        if (line.startsWith(CONTENTS_STARTER)) {
            String itemsList = line.substring(CONTENTS_STARTER.length());
            String[] itemNames = itemsList.split(",");
            for (String itemName : itemNames) {
                try {
                    add(d.getItem(itemName));
		    if(d.getItem(itemName) == null){
			throw new NoItemException();
		    }
                } catch (NoItemException e) {
                    System.out.println("No such item " + itemName + ".");
		}
	    }
            s.nextLine();  // Consume "---".
	}
	}
    /**Sets a false flag for other describe() method use.
    **/
    public String describe() {
        return describe(false);
    }
    /**Returns a message describing this Room. If the player has been in the Room before, the message only includes the title, 
     * the contents of the Room (including any NPCs or Guards), and the Exits.  If they have not been here before, 
     * this message will inlcude the Room's title, the Room description, the contents of the Room, 
     * any NPCs or Guards in the Room, and any Exits in the Room.
    **/
    public String describe(boolean fullDesc) {
        String description;
        if (beenHere && !fullDesc) {
            description = title;
        } else {
            description = title + "\n" + desc;
        }
        for (Item item : contents) {
            description += "\nThere is a " + item.getPrimaryName() + " here.";
        }
        if (contents.size() > 0) { description += "\n"; }
        if (!beenHere || fullDesc) {
            for (Exit exit : exits) {
                description += "\n" + exit.describe();
            }
        }
        beenHere = true;
        return description;
    }
    /**
     * Allows the player to leave their current Room and enter another Room which is joined by an Exit.
     */
    public Room leaveBy(String dir) {
        for (Exit exit : exits) {
            if (exit.getDir().equals(dir)) {
                return exit.getDest();
            }
        }
        return null;
    }

    /**
     * Adds an exit to this room.
     */
    void addExit(Exit exit) {
        exits.add(exit);
    }
	/**
	 * Adds an item to this room's inventory.
	 */
    void add(Item item) {
        contents.add(item);
    }
	/**
	 * Removes an item from this room's inventory.
	 */
    void remove(Item item) {
        contents.remove(item);
    }
	/**
	 * Returns an item based on a given name.
	 * @throws NoItemException The item is not found.
	 */
    Item getItemNamed(String name) throws NoItemException {
        for (Item item : contents) {
            if (item.goesBy(name)) {
                return item;
            }
        }
        throw new NoItemException();
    }
	/**
	 * Returns the contents of this room's inventory. This only returns items; this does not return NPCs/Guards or Exits.
	 */
    ArrayList<Item> getContents() {
        return contents;
    }
}
