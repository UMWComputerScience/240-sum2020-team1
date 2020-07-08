/**
 * Extends Exit and can be manipulated by being able to be locked,
 * unlocked, or permanentaly destroyed. 
 * 
 * Once locked, an exit cannot be used unless it is unlocked again.
 * Once destroyed, an exit cannot be used anymore, nor unlocked. 
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
