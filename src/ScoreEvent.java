class ScoreEvent extends Event{
	private int value;

	ScoreEvent(int value){
		this.value = value;
	}

	String callEvent(){
		GameState.instance().increaseScore(value);
		return "";
	}
}
