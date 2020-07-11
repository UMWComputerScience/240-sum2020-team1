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
	 * Checks the status of an exit to see of it can be
	 * currently used.
	 */
	public boolean getStatus(){
	    return true;
	}

	/**
	 * Changes the condition of an exit to be locked.
	 * Note that this prevents the user from usimg this exit.*/
	public void lockExit(){

	}

	/**
	 * Changes the condition of an exit to be unlocked.
	 * Note that this allows the user to use this exit.*/
	public void unlockExit(){

	}
	
	/**
	 * Changes the condition of an exit to be destroyed. 
	 * Note that this exit can no longer be used once destroyed.*/
	public void destroyExit(){

	}
}
