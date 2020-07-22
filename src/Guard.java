import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
/**
 * A Guard behaves as a {@link NonPlayableCharacter}, with the addition of an ability
 * to unlock a locked exit.
 */
class Guard/* extends NonPlayerCharacter*/ {
	private String exitKey;
	private String name;
	private Hashtable<String, String> messages;
		
	Guard(Scanner s) {
		init();
		ArrayList<String> data = new ArrayList<String>();
		data.add(s.nextLine());
		String testLine = s.nextLine();
		while(!testLine.equals("---")){
			data.add(s.nextLine());
		}
		String unlock = data.get(-1);
		data.remove(-1);
		
		this.name = data.get(0);
		for (int i = 1;i<data.size();i++){
			String converseLine = data.get(i);
			String [] messagePart = converseLine.split(":");
			this.messages.put(messagePart[0],messagePart[1]);
		}

		String [] unlockArr = unlock.split(":");
		String unlockDirr = unlockArr[1];
		this.exitKey = unlockDirr;
		}

	private void init(){
		messages = new Hashtable<String, String>();
	}
		
	/**
	 * Unlocks a locked exit. Note that there is no return type as a Guard can 
	 * unlock only one specific exit.
	 */
	public void unlockExit(){

	}
}
