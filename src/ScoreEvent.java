import java.util.Arrays;

class ScoreEvent extends Event{
	private int scoreVal;

	ScoreEvent(String s){
		String [] splitS = s.split("(");
		String unparsedNum = splits[1];
		String [] splitNum = unparsedNum.split(")");
		String tempScoreVal = splitNum[0];
		int tempIntScoreVal = Integer.parseInt(tempScoreVal);
		this.scoreVal = tempIntScoreVal;	
	}

	String callEvent(){
		GameState.instance().increaseScore(scoreVal);
		return "";		
	}
}
