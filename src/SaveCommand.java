
class SaveCommand extends Command {

    private static String DEFAULT_SAVE_FILENAME = "zork";

    private String saveFilename;

    SaveCommand(String saveFilename) {
        if (saveFilename == null || saveFilename.length() == 0) {
            this.saveFilename = DEFAULT_SAVE_FILENAME;
        } else {
            this.saveFilename = saveFilename;
        }
	/**Given the name of a save file name, this constructor creates a new save file that is meant to save the player's current progress to that file.
	**/
    }

    public String execute() {
        try {
            GameState.instance().store(saveFilename);
            return "Data saved to " + saveFilename +
                GameState.SAVE_FILE_EXTENSION + ".\n";
        } catch (Exception e) {
            System.err.println("Couldn't save!");
            e.printStackTrace();
            return "";
        }
	/**Saves the player's current progress to a previously defined save file.
	@throws Exception Could not create a save file.
	@throws IllegalSaveFormatException Could not read a currently existing save file.
	**/
    }
}
