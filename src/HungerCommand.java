/** creates a HungerCommand object used for troubleshooting the hunger system. */
class HungerCommand extends Command {
	private int currentHunger;
	private int currentHungerScore;
	private int hungerInc;

/** creates a command to allow a debugger to check the current hunger system settings.*/
	HungerCommand(){
	//	currentHunger = GameState.instance().hungerCheck();
		currentHungerScore = GameState.instance().hungerCountCheck();
		hungerInc = GameState.instance().hungerIncCheck();
	}
	
/**if debugging is turned on, displays the hunger system stats to the player. Otherwise it returns a "I don't know what you mean" Message */
	public String execute(){
		String returnString = "";
		if(GameState.instance().getTest()==true){
			returnString = "Current hunger:"+currentHunger+"\nCurrent hungerCount: "+currentHungerScore+"\nHunger Incriment: "+hungerInc+"\n";
		}
		else{
			returnString = "I'm not sure what you mean by \"hunger\" \n";
			}
		return returnString;
	}	
}
