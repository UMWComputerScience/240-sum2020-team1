public class DieEvent extends Event{
private String deathMessage;

    DieEvent(String deathMessage){
	deathMessage = "You done goofed. Play better next time, l00s3r";
	this.deathMessage = deathMessage;
    }

    String callEvent(){
	GameState.instance().setHealth(0);
	System.out.println(deathMessage);
	//int invSize = GameState.instance().getInventory().size();
	//for(int i = 0; i<invSize;i++){
//		Item thing = GameState.instance().getInventory().get(i);
//		try{
//		GameState.instance().removeFromInventory(thing);
//		}
//		catch (NoItemException n){}
//	}
//	String firstRoom = GameState.instance().getDungeon().getEntry().getTitle();
//	System.out.println("The magical fairy Stephan comes fluttering by and takes pity on you.");
//	System.out.println(" \"I shall resurrect you and return you to the start of this dungeon.\" ");
//	System.out.println("With a wave of his magic wand your vision turns white and a moment later you return to consiousness, looking at the inside of the "+firstRoom+".");
//	GameState.instance().returnToStart();
//	System.exit(0);
	//return "";
	return deathMessage;
    }
}
