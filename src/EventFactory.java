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
	if(GameState.instance().getTest()){
		System.out.println(eventType + ":" + item);
	}
//	eventType = eventType.toLowerCase();
	eventType = eventType;
	if(GameState.instance().getTest()==true){
	System.out.println("Event type: "+eventType);
}
        if (eventType.contains("Wound")){	
            return new WoundEvent(eventType);
        }
        if (eventType.contains("Drop")) {
            return new DropEvent(item);
        }
        if (eventType.contains("Teleport")) {
            return new TeleportEvent(eventType);
        }
        if (eventType.contains("Disappear")) {
		String eventArgs = eventType+":"+item;
		if(GameState.instance().getTest()==true){
		System.out.println("Event- "+eventType+" called");
		System.out.println("eventArgs:"+eventArgs);
		}
//}		String eventArgs = eventType+":"+item;
            return new DisappearEvent(eventArgs);
	
        }
        if (eventType.contains("Score")) {
            return new ScoreEvent(eventType);
        }
        if (eventType.contains("Die")) {
            return new DieEvent(eventType);
        }
        if (eventType.contains("Transform")) {
		if(GameState.instance().getTest()==true){
		System.out.println("Test line");
		}
		String eventArgs = eventType+":"+item;
            return new TransformEvent(eventArgs);
        }
        if (eventType.contains("Win")) {
            return new WinEvent(eventType);
        }
	return new UnknownEvent(eventType);
    }
}
