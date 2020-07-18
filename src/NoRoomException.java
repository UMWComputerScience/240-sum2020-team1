/** Exception to be thrown if the player attempts to move into a room that doesn't exist or 
* if a room is called to be created, but there is no information for the room to be created.
*/

public class NoRoomException extends Exception {
     /**Displays a message detailing that there is no room found.
      * */
     	public NoRoomException(String room)
        {
            super(room);
        //super(item);
        //System.out.println("No such room");       }
        }
}
