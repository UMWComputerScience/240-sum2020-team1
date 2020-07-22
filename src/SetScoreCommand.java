/**command object used for setting the player score to a given value. Only used for testing and debugging purposes */

class SetScoreCommand extends Command{
	private int score;

	SetScoreCommand(int score){
		this.score = score;}

	public String execute(){
		if(GameState.instance().getTest()==true){
		GameState.instance().setScore(score);
		return "Score has been set to "+score+".\n";
		}
		else{
		return "I do not understand that command.";
		}
		
		}

}
