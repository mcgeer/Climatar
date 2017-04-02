package game.climatar.news;
import java.util.List;

import game.climatar.map.Nation;

public enum NewsEvents {
	POLITICAL,
	WEATHER,
	GHG;
	
	private String[] BaseEvent;
	private static EventType action;
	private static Nation rep;
	
	
	
	private static Nation getEventNation(){
		return rep;
	}
	private static EventType getEventType(){
		return action;
	}
	
	private static void newsExecute(){
		
	}

	
	
	
}

