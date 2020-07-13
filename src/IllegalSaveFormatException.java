
/** exception to be thrown if an invalid or incompatible .sav file is detected.
*/
 class IllegalSaveFormatException extends Exception {
/**Declares exception.
 * */
	public IllegalSaveFormatException(String e) {
            super(e);
        }
    }
