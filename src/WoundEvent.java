class WoundEvent extends Event{
	private int amountOfWound;

	WoundEvent(int amountOfWound){
	    this.amountOfWound = amountOfWound;
	}

	String callEvent(){
	    GameState.instance().wound(amountOfWound);
	    return "You got oofed";
	}
}
