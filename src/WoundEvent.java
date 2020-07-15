class WoundEvent extends Event{
	private int amountOfWound;

	WoundEvent(String amountOfWound){
            int openParan = amountOfWound.indexOf("(");
	    int closeParan = amountOfWound.indexOf(")");
     	    String commandValue= amountOfWound.substring(openParan,closeParan);
	    this.amountOfWound =Integer.parseInt(commandValue);
	}

	String callEvent(){
	    GameState.instance().wound(amountOfWound);
	    return "You got oofed";
	}
}
