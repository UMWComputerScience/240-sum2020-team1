public class DieEvent extends Event{
private String deathMessage;

    DieEvent(String deathMessage){
	deathMessage = "You done goofed. Play better next time, l00s3r";
	this.deathMessage = deathMessage;
    }
    public String getDeathMessage(){
	    return deathMessage;
    }
	
	
    String callEvent(){
//	GameState.instance().setHealth(0);
	if(GameState.instance().getTest()==true){
	System.out.println(deathMessage);
	}
	while(0<GameState.instance().getInventory().size()){
		Item t = GameState.instance().getInventory().get(0);
		GameState.instance().getInventory().remove(t);
		GameState.instance().getAdventurersCurrentRoom().add(t);
		}
	GameState.instance().setHealth(0);

	return "";
    }
}
