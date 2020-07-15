import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

class EventFactory {

    private static EventFactory theInstance;
    

    public static synchronized EventFactory instance() {

        if (theInstance == null) {
            theInstance = new EventFactory();
        }
        return theInstance;
    }

    private EventFactory(){
    }

    Event parse(String eventType){
	
    }
}
