/**
 * Extends Exit and can be manipulated by being able to be locked,
 * unlocked, or permanentaly destroyed. 
 *
 * the instance variables isLocked and isDestroyed are checked by 
 * the movement command to make sure the played doesn't move
 * through an unusable exit. 
 */
class LockableExit extends Exit{
	public boolean isLocked;
	public boolean isDestroyed;
	
	/**
	 * checks the status of an exit, therefore isLocked and
	 * isDestroyed are checked by the moveCommand class to
	 * see if the exit can be used.
	 *
	 * returns the boolean values of isLocked and isDestroyed
	 */
	public boolean getStatus(){
	    return true;
	}

	/**
	 * changes the isLocked boolean value to true
	 *
	 * is called by the Room class when a new CloseRoomCommand
	 * is instantiated.
	 * */
	public void lockExit(){

	}

	/**
	 * changes the isLocked boolean value to false
	 *
	 * is called by the Room class when a new OpenRoomCommand
	 * is instantiated.
	 * */
	public void unlockExit(){

	}
	
	/**
	 * changes the isDestroyed boolean value to true
	 * 
	 * is called by the Room class when a new CloseRoomCommand
	 * is instantiated.
	 * */
	public void destroyExit(){

	}
}
