import java.util.ArrayList;
//import java.util.Array;
/** Commiand to create a craftable item.
*/
class craftCommand extends Command {
	private String itemToCraft;
/**Creates a command to allow the player to craft a craftable item.
 * */
	craftCommand(String command) {
		this.itemToCraft = command;
	}
/**return a string comprised of items that are needed to craft a supplied item */
	private String neededItems(String item){
	String neededItemList = "";
	try{Item itemToCraft = GameState.instance().getDungeon().getItem(item);
		if(GameState.instance().getTest()==true){
                System.out.println("Item requested:"+item);
                System.out.println("Item returned:"+itemToCraft.getPrimaryName());
                System.out.println("Items needed to craft item:"+itemToCraft.getMessageForVerb("recipe"));
        	}
	neededItemList = itemToCraft.getMessageForVerb("recipe");
	}
	catch(NoItemException e){};{}
	return neededItemList;	
	}
/**Allows the player to combine two ingredients to create a craftable item.*/
	public String execute() {
		String itemCheck = neededItems(itemToCraft);
		String[] itemsNeeded = itemCheck.split("-");
		if(GameState.instance().getTest()==true){
			System.out.println("Execute command in craftCommand");
		}
//		String[] itemsNeeded = itemCheck.split("-");
		if(GameState.instance().getTest()==true){
			System.out.println("Items needed:");
			for(int i = 0;i<itemsNeeded.length;i++){
				System.out.println("I");
				System.out.println("Item "+i+": "+itemsNeeded[i]);
			}
		}
		String returnString = neededItems(itemToCraft);
		
	return returnString;	
	}

}
