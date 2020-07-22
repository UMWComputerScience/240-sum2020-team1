/**command object that allows the player to see their carrying capacity. Only used for debugging and testing. */

class CarryCommand extends Command {
	private String sting = "";

/**command that takes an empty string and displays the players carrying capacity */
	CarryCommand(String sting){
		this.sting = sting;
		}
	
	public String execute(){
		String carry ="";
		if(GameState.instance().getTest()==true){
		return "Current player carry weigh is:"+(GameState.instance().getStrength())+"\n and you are carrying: "+GameState.instance().getAdventurersCurrentWeight()+" pounds.\n";
		}
		else{
		return "I'm not sure what you mean by \""+sting+"\".\n";
		}	
	}
}
