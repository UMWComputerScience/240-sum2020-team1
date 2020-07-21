
import java.util.Scanner;
/**
 * An exit is used to transport the player from one room to another. An exit from one
 * room has a corresponding exit in the next room in the opposite direction, allowing
 * the player to traverse between rooms as intended.
 */
public class Exit {
/** empty constructor that takes no parameters.
*/
    public Exit(){
    }

    private String dir;
    private Room src, dest;
    private boolean lockable;
    private boolean isLocked;
/** constructor for instantianting exits manually. Needs to know the direction that the player can pass through the exit in, what room they are passing from, and what room they are passing to.
* @param dir direction of travel for the exit.
* @param src the source room for the individual exit
* @param dest the destination room for this individual exit.
*/
    Exit(String dir, Room src, Room dest) {
        init();
        this.dir = dir;
        this.src = src;
        this.dest = dest;
	
        src.addExit(this);
    }

    /** Given a Scanner object, reads and returns an Exit object representing it. 
        @param d The dungeon that contains this exit (so that Room objects 
        may be obtained.)
        @throws NoExitException The reader object is not positioned at the
        start of an exit entry in a .zork file. A side effect of this is the reader's cursor
        is now positioned one line past where it was.
        @throws IllegalDungeonFormatException A structural problem with the
        dungeon file itself, detected when trying to read this room.
     */
    Exit(Scanner s, Dungeon d) throws NoExitException,
       IllegalDungeonFormatException, NoRoomException {

        init();
        String srcTitle = s.nextLine();
        if (srcTitle.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoExitException();
        }
        this.src = d.getRoom(srcTitle);
        this.dir = s.nextLine();
        this.dest = d.getRoom(s.nextLine());
	//System.out.println(src.getTitle() + " " + dir + " " + dest.getTitle());
        
        // I'm an Exit object. Great. Add me as an exit to my source Room too,
        // though.
        src.addExit(this);

        // throw away delimiter
	String nextLine = s.nextLine();
	String g = nextLine;
	if(nextLine.contains("lockable")){
		this.lockable = true;
		g = s.nextLine();
		if(nextLine.contains("true")){
			this.isLocked = true;
		}
		else if(nextLine.contains("false")){
			this.isLocked = false;
		}
		g = s.nextLine();
	}
	else{
		lockable = false;
		isLocked = false;
	
	if (!nextLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after exit.");
        }
	//}
    }
}

    /**
     * Handles common initialization
     */
    private void init() {
    }
	/**
	 * Returns a description containing an exit with it's
	 * associated room destination.
	 */
    String describe() {
        return "You can go " + dir + " to " + dest.getTitle() + ".";
    }
	/**
	 * Returns the direction of THIS exit.
	 */
    String getDir() { return dir; }
	/**
	 * Returns the room which has THIS exit.
	 */
    Room getSrc() { return src; }

    /**
     * Returns the room in which the player will be in
     * after using THIS exit.
     */
    Room getDest() { return dest; }
}
