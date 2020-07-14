
class WoundCommand extends Command{
	private int wounds;

	WoundCommand (int wounds){
		this.wounds = wounds;
	}

	public String execute(){
		GameState.instance().wound(wounds);
		return "You have been hurt";
	}
}	
