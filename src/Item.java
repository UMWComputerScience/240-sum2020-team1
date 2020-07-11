
import java.util.Scanner;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;
/** An Item is an object that can be moved between a room and the player's inventory,
 * or directly added to the inventory through crafting or an encounter with an NPC.
 *
 * Note that an Item can also destroy an exit, transform into another item, or move
 * the player to another room. 
 */
public class Item {
    /**Empty, generic constructor that creates an Item
    **/
    public Item(){
    }

    private String primaryName;
    private int weight;
    private Hashtable<String,String> messages;
    private Set<String> aliases;

    /**
     * Creates an Item object by reading the contents of a .zork file.
     * @throws NoItemException The .zork file does not have another Item to be read in.
     */
    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {

        messages = new Hashtable<String,String>();
        aliases = new HashSet<String>();

        // Read item name.
        String names[] = s.nextLine().split(",");
        if (names[0].equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }
        primaryName = names[0];
        for (int i=1; i<names.length; i++) {
            aliases.add(names[i]);
        }

        // Read item weight.
        weight = Integer.valueOf(s.nextLine());

        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }
            String[] verbParts = verbLine.split(":");
            messages.put(verbParts[0],verbParts[1]);
            
            verbLine = s.nextLine();
        }
    }
	/**
	 * Returns an integer representing the item's weight.
	 */
    int getWeight() {
        return weight;
    }
	/**
	 * Returns true if the supplied string is found among this Item's aliases.  Returns false in all other cases.
	 */
    boolean goesBy(String name) {
/* returns boolean if the supplied string is found in the items primaryName attribute or alias hashset.
 */
        if (this.primaryName.equals(name)) {
            return true;
        }
        for (String alias : this.aliases) {
            if (alias.equals(name)) {
                return true;
            }
        }
        return false;
    }
	/**
	 * Returns the primary name of an Item.
	 */
    String getPrimaryName() { return primaryName; }
	/**
	 * Returns a message in response to a specified action.
	 */
    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }
	/**
	 * Returns an item's primary name.
	 */
    public String toString() {
        return primaryName;
    }
}
