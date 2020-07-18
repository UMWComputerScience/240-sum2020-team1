/**Used to view the player's score and
 * description of their rank.
*/

class ScoreCommand extends Command {
	//private int score;
	private String [] ranks = {"noob","intermediate","experienced","master","Stephen-like"};
	/**
	 * The constructor generates a commant that will check the player's score.
	 */
	ScoreCommand(){
	
	/*Given a string resembling the player's score, this constructor generates a command that is meant to check the score and display a short message to the player which varies depending on how high or low the score is.
	**/
	}
	
	/**This method checks the score and displays a short message to the player which varies depending on how high or low the score is.
	*/
	public String execute() {
	String rank ="";
	int Score = GameState.instance().getScore();
	if (Score <= 5){
		rank = ranks[0];
	}
	else if (Score <= 10 && Score >= 6){
		rank = ranks[1];
	}
	else if (Score <= 15 && Score >= 11){
		rank = ranks[2];
	}
	else if (Score <= 20 && Score >= 16){
		rank = ranks[3];
	}else { 
		rank = ranks[4];
	}
	
	String scoreString = "You have accumulated " + GameState.instance().getScore() + " points. This gives you a rank of " + rank + "." + "\n";
	return scoreString;
	}
}

