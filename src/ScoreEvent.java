import java.util.Arrays;

class ScoreEvent extends Event{
	private int scoreVal;

	ScoreEvent(String s){
		int iStart = s.indexOf("(");
		int iEnd = s.indexOf(")");
		int value = Integer.parseInt(s.substring(iStart+1,iEnd));
//		String[] splitS = s.split("(");
//		String unparsedNum = splitS[1];
//		String[] splitNum = unparsedNum.split(")");
//		String tempScoreVal = splitNum[0];
//		int tempIntScoreVal = Integer.parseInt(l);
		this.scoreVal = value;
	}

	String callEvent(){
		if(GameState.test){
		System.out.println("Score Value: "+scoreVal);
		}
		GameState.instance().increaseScore(scoreVal);
	
		return "";
	}
}
