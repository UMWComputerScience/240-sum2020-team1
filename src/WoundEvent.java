class woundEvent extends Event{
	private int amountOfWound;

	String callEvent(){
	    GameState.instance().wound(amountOfWound);
	    return "You got oofed";
	}
}
