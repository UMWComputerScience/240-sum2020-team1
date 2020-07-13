/** exception to be thrown if an exit is called, but no such exists, or to be called
* if the player attempts to pass through a locked exit.
*/

public class ClosedExitException extends Exception {
        public ClosedExitException(String e){
            super(e);
        //super(item);
        System.out.println("ClosedExitException.");       
	}
}
