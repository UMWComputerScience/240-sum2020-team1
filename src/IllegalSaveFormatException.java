
/** exception to be thrown is an invalid or incompatible .sav file is detected.
*/
public static class IllegalSaveFormatException extends Exception {
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }
