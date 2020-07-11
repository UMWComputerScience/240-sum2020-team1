    /**This class is used to initiate the process of saving the player's progress to a .sav file.
    **/
class SaveCommand extends Command {

    private static String DEFAULT_SAVE_FILENAME = "zork";

    private String saveFilename;
    /**Given the name of a save file, this constructor passes the save file name that is meant to be used to save the player's current progress.
    **/
    SaveCommand(String saveFilename) {
        if (saveFilename == null || saveFilename.length() == 0) {
            this.saveFilename = DEFAULT_SAVE_FILENAME;
        } else {
            this.saveFilename = saveFilename;
        }
    }
    
    /**Will pass the save file name from SaveCommand to activate the save file process.
    **/
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
    }
}
