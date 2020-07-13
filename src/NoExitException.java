/**Exception to be thrown is the player attempts to use an exit that does not exist, or
* if an exit is called to be created, during hydration, and there is no such information
* for an exit to be created.
*/

public class NoExitException extends Exception{}
