/**Used to view the player's score and
 * description of their rank.
*/

class ScoreCommand extends Command {
	private String score;
	private String [] ranks = {"noob","intermediate","experienced","master"}
	/**
	 * The constructor generates a commant that will check the player's score.
	 */
	ScoreCommand(String score){
	this.score = score;
	/*Given a string resembling the player's score, this constructor generates a command that is meant to check the score and display a short message to the player which varies depending on how high or low the score is.
	**/
	}
	
	/**This method checks the score and displays a short message to the player which varies depending on how high or low the score is.
	*/
	public String execute() {
	String rank ="";
	if (Gamestate.getScore <= 5){
		rank = ranks[0];
	}
	if (Gamestate.getScore <= 10){
		rank = ranks[1];
	}
	if (Gamestate.getScore <= 15){
		rank = ranks[2];
	}
	if (Gamestate.getScore <= 20){
		rank = ranks[3];
	}
	String scoreString = "You have accumulated " + GameState.instance().getScore() + " points. This gives you a rank of " + rank + ".";
	return scoreString;
	}
}

