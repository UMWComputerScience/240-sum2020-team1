public class NoItemException extends Exception {
	public void  NoItemExceptionException(String item) 
	{
	/** NoItemException thrown when an item is called and no such
	* item found.
	*/
	super(item);
	System.out.println("No "+item+" found.");	}	
	}
