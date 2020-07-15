public class DieEvent extends Event{

    DieEvent(){
    }

    String callEvent(){
	GameState.instance().setHealth(0);
	System.out.println("You done goofed. Play better next time, l00s3r");
	System.exit(0);
	return "";
    }
}
