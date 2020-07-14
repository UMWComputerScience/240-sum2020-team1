/**Used to view the player's score and
 * description of their rank.
*/

class ScoreCommand extends Command {
	private String score;
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
	return "does this work";
	}
}

