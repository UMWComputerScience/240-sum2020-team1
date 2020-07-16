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

    Event parse(String eventType, String item){

	eventType = eventType.toLowerCase();
	if(GameState.instance().getTest()==true){
	System.out.println("Event type: "+eventType);
}
        if (eventType.contains("wound")){	
            return new WoundEvent(eventType);
        }
        if (eventType.contains("drop")) {
            return new DropEvent(eventType);
        }
        if (eventType.contains("teleport")) {
            return new TeleportEvent(eventType);
        }
        if (eventType.contains("disappear")) {
		if(GameState.instance().getTest()==true){
		System.out.println("Event "+eventType+" called");
}		String eventArgs = eventType+":"+item;
            return new DisappearEvent(eventArgs);
	
        }
        if (eventType.contains("score")) {
            return new ScoreEvent(eventType);
        }
        if (eventType.contains("die")) {
            return new DieEvent(eventType);
        }
        if (eventType.contains("transform")) {
            return new TransformEvent(eventType);
        }
        if (eventType.contains("win")) {
            return new WinEvent(eventType);
        }
	return new UnknownEvent(eventType);
    }
}
