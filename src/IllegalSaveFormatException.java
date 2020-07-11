
/** exception to be thrown if an invalid or incompatible .sav file is detected.
*/
public static class IllegalSaveFormatException extends Exception {
/**Declares exception.
 * */
	public IllegalSaveFormatException(String e) {
            super(e);
        }
    }
