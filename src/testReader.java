import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;
public class testReader{

    public static String TOP_LEVEL_DELIM = "===";
    public static String SECOND_LEVEL_DELIM = "---";

    // Variables relating to dungeon file (.zork) storage.
    public static String ROOMS_MARKER = "Rooms:";
    public static String EXITS_MARKER = "Exits:";
    public static String ITEMS_MARKER = "Items:";

    // Variables relating to game state (.sav) storage.
    static String FILENAME_LEADER = "Dungeon file: ";
    static String ROOM_STATES_MARKER = "Room states:";
    private Hashtable<String,String> StringTest= new Hashtable<String,String>();
	public String filename = "test4.zork";
	public static void main(String args[]) throws Exception, FileNotFoundException{
    Scanner s = new Scanner(new FileReader("test4.zork"));
	String name = s.nextLine();
	String testLine = s.nextLine();
	while(!testLine.equals("+++")){
		System.out.println("testline:"+testLine);
		if(testLine.equals("ThisIsANewLine")){
		System.out.println("Yeah new line happy dance!");}
		if(testLine.contains("ThisWillGiveAnumber(")){
		int iStart = testLine.indexOf( "(");
		int iEnd = testLine.indexOf(")");
		int value = Integer.parseInt(testLine.substring(iStart+1,iEnd));
		System.out.println("Number: "+value);
		}
		testLine = s.nextLine();
	}

	
}
}
