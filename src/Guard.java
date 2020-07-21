/**
 * A Guard behaves as a {@link NonPlayableCharacter}, with the addition of an ability
 * to unlock a locked exit.
 */
class Guard extends NonPlayerCharacter {
	private String exitKey;
	
		
	Guard(Scanner s) {
		String [] data = new ArrayList<String>();
		data[0] = s.nextLine();
		int i = 1;
		String testLine = s.nextLine();
		while(!testLine.equals("---")){
			data[i] = s.nextLine();
			i++;
		}
		String unlock = data[-1];
		data.remove(-1);
		NonPlayerCharacter(data);

		String [] unlockArr = unlock.split(":");
		String unlockDirr = unlockArr[1];
		this.exitKey = unlockDirr;
		}
		
	/**
	 * Unlocks a locked exit. Note that there is no return type as a Guard can 
	 * unlock only one specific exit.
	 */
	public void unlockExit(){

	}
}
