/** exception to be thrown is an invalid or incompatible .zork file is detected
* when loading a game or starting a new game. 
*/  
 public static class IllegalDungeonFormatException extends Exception {
        public IllegalDungeonFormatException(String e) {
            super(e);
        }
    }

