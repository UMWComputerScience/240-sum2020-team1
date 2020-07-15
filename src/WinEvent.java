class WinEvent extends Event{

	private String winMessage;

	WinEvent(String winMessage){
		winMessage = "Congratulations! You've beaten the game!";
		this.winMessage = winMessage;
	}
	String callEvent(){
		System.out.println(winMessage);
		System.exit(0);
		return "";
	}
}
