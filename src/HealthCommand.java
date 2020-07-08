import java.util.Array;
/** command object that returns a meggase describing the players current health status.
**<b style="color:red;">NEED TO REVIEW</b>
*/

class HealthCommand extends Command {
	private String health;
	private String[] healthMessages;
	private String[] hungerMessage;
	
	HealthCommand(String health) {
		this.health = health;
		this.messages =  {
			"You're dead, so yeah. You're health doesn't really matter, does it?",
			"Vision... fading... badly hurt... must... recover...",
			"Your body is raked with pain from your numerous injuries.",
			"You're exhausted and badly hurt. Be careful. The next hit you take could well be your last.",
			"Blood drips from your numerous wounds, your muscles ache, your back is screaming. This is going to be an amazing story to tell folks!",
			"Yeah… I'm pretty sure that your arm isn't supposed to have 2 elbows. You're going to need to put some TLC into yourself.",
			"You are hurt and pretty bad. You should probably find some food.",
			"So on the bright side you don't have a hole in your head. Other places in your body however…",
			"You breath heavily and your ribs ache. Definitly need to find something to eat and to heal yourself up in the near future.",
			"Yeah, you're going to have a few scars from this trip.",
			"'Tis but a minor flesh wound. Barely anything of concern",
			"You are injured but not seriously so. A quick snack will pick you up.",
			"You feel great! Ready to take on the world!"
			}
		}
/** gets the players current health and returns a message describing the relative condition of the players health.
* If the player's health is maxed out, it will instead return a message conserning the players hunger.
* @return message describing players health.
* @Author Michael Cividanes
*/

	public String execute() {
		}
}
	

