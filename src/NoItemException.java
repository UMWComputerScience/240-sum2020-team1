/** exception to be thrown if an item is called for, but no such item is found.
*/

public class NoItemException extends Exception {
	public NoItemException(String item) 
	{
	    super(item);
	//super(item);
	System.out.println("No "+item+" found.");	}	
	}
