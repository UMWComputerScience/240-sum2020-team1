
import java.util.Scanner;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;

public class Item {
    public Item(){
    }
/** Class for instantiating items that can be held in the GameState, NPC, Room, and GameState inventories. Items 
* require a name to be created.
*/
    static class NoItemException extends Exception {}

    private String primaryName;
    private int weight;
    private Hashtable<String,String> messages;
    private Set<String> aliases;


    Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {
/** constructor for itme class. uses scanner to read .zork/.sav file into instatiation 
* Items have a messages hashTable, which take string as the key, and string as value.
* items also have an alias HashSet that take a String.
*/

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

    int getWeight() {
/** returns an integer representing the items weight
*/
        return weight;
    }

    boolean goesBy(String name) {
/** returns boolean if the supplied string is found in the items primaryName attribute or alias hashset.
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

    String getPrimaryName() { return primaryName; }

    public String getMessageForVerb(String verb) {
/** returns the string that corrosponds with the supplied verb in the ItemSpeccificCommand. 
*/
        return messages.get(verb);
    }

    public String toString() {
/** returns item's primary name
*/
        return primaryName;
    }
}
