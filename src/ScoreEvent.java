class ScoreEvent extends Event{
	private String value;

	ScoreEvent(String value){
		this.value = value;
	}

	String callEvent(){
		GameState.instance().increaseScore(Integer.parseInt(value));
		return "";
	}
}
