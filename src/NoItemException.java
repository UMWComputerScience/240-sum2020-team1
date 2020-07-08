public class NoItemException extends Exception {
/** NoItemException thrown when an item is called and no such
* item found.
*
*/

	public NoItemException(String item) 
	{
	    super(item);
	//super(item);
	System.out.println("No "+item+" found.");	}	
	}
