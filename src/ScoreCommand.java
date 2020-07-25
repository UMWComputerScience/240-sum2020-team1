/**Used to view the player's score and
 * description of their rank.
*/

class ScoreCommand extends Command {
	//private int score;
	private String [] ranks = {"Pleeb","Noob","Pawn","Intermediate","Experienced","Great Fighter","True Adventurer","Warrior","Knight","Valiant Knight","Paladin","Exemplary Warrior","Exemplar","Master","Stephen-like"};
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
	else if (Score >= 6 && Score <= 10){
		rank = ranks[1];
	}
	else if (Score >= 11 && Score <= 15){
		rank = ranks[2];
	}
	else if (Score >= 16 && Score <= 20){
		rank = ranks[3];
	}
	else if (Score >= 21 && Score <= 25){
		rank = ranks[4];
	}
	else if (Score >= 26 && Score <= 30){
		rank = ranks[5];
	}
	else if (Score >= 31 && Score <= 35){
		rank = ranks[6];
	}
	else if (Score >= 36 && Score <= 40){
		rank = ranks[7];
	}
	else if (Score >= 41 && Score <= 45){
		rank = ranks[8];
	}
	else if (Score >= 46 && Score <= 50){
		rank = ranks[9];
	}
	else if (Score >= 51 && Score <= 55){
		rank = ranks[10];
	}
	else if (Score >= 56 && Score <= 60){
		rank = ranks[11];
	}
	else if (Score >= 61 && Score <= 65){
		rank = ranks[12];
	}
	else if (Score >= 66 && Score <= 70){
		rank = ranks[13];
	}
	else { 
			rank = ranks[14];
	}
	
	String scoreString = "You have accumulated " + GameState.instance().getScore() + " points. This gives you a rank of " + rank + "." + "\n";
	return scoreString;
	}
}

