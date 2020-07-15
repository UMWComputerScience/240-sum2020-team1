import java.util.Scanner;

/** reprsents a verb that triggers an event such as wearing a necklace adding 1 to your score.
*/
public class ItemEvent{
	public ItemEvent() {
	}
	
	private String message;
	private String [] command;
	ItemEvent(String m, Striing [] c){
		this.message = m;
		this.command =c;
	}	
	ItemEvent(String s) throws IllegalDungeonFormatException {
//		String names[] = s.NextLine().split(":");
	}

	String getMessage(){
	return this.message;}
	
	String [] getCommand(){
	return this.command;
	}


}
