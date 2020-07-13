/** Command to create a craftable item.
*/
class craftCommand extends Command {
	private String craftCommand;
/**Creates a command to allow the player to craft a craftable item.
 * */
	craftCommand(String command) {
		this.craftCommand = command;
	}
	/**Allows the player to combine two ingredients to create a craftable item.*/
	public String execute() {
//	if (craftCommand == null || craftCommand.trim().length() == 0) {
//		return "Craft what?\n";
//		}
//	try(){
	return "Craft Command";	
	}

}
