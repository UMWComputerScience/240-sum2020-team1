/** exception to be thrown if a speccificed NonPlayerCharacter is called for,
* but no such NonPlayerCharacter is found in the current room.
*/
public class NoNPCPresentException extends Exception {
/** returns a message stating that there is noone named that in the current room.
*/
        public NoNPCPresentException(String name)
        {
            super(name);
        //super(item);
        System.out.println("There is noone known as "+name+" in this room.");       }
        }
