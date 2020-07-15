class UnknownEvent extends Event{
	private String blahblahblah;

	UnknownEvent(String blahblahblah){
		this.blahblahblah = blahblahblah;
	}

	String callEvent(){
		return "Sorry, I don't understand " + blahblahblah + ".";
	}
}
