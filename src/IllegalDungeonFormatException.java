/** Exception to be thrown if an invalid or incompatible .zork file is detected
* when loading a save game or starting a new game. 
*/  
public class  IllegalDungeonFormatException extends Exception {
	public IllegalDungeonFormatException(String e) {
		super(e);
	}
}
