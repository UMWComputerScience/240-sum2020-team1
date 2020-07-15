public class DieEvent extends Event{
private String deathMessage;

    DieEvent(String deathMessage){
	deathMessage = "You done goofed. Play better next time, l00s3r";
	this.deathMessage = deathMessage;
    }

    String callEvent(){
	GameState.instance().setHealth(0);
	System.out.println(deathMessage);
	System.exit(0);
	return "";
    }
}
