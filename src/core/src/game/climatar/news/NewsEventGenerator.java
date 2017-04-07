package game.climatar.news;
import java.util.Collections;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.badlogic.gdx.Gdx;

import game.climatar.GameState;
import game.climatar.GameState.WorldProperty;
import game.climatar.map.Nation;
import game.climatar.news.ConseqType.Consequence;
import game.climatar.news.NewsEvent.NewsType;

public class NewsEventGenerator {
    private Nation playerNation;
    private LinkedList<NewsEvent> WorldEvents;
    private LinkedList<NewsEvent> PlayerEvents;

    public NewsEventGenerator(Nation n) {
        playerNation = n;

        readEvents();
        
        sortEvents();
    }

    private void readEvents() {
		System.out.println("READING EVENTS");
        WorldEvents = new LinkedList<NewsEvent>();
        PlayerEvents= new LinkedList<NewsEvent>();
        boolean isPassive;
        JSONParser parser = new JSONParser();
        try {
			
            JSONObject json = (JSONObject) parser.parse(Gdx.files.internal("Events.json").readString());
			JSONArray readArray = (JSONArray) json.get("Events");
            for (Object obj : readArray) {
                isPassive=false;
                JSONObject newEvent = (JSONObject) obj;
                NewsEvent storeEvent;
				System.out.println(playerNation.getName());
				System.out.println((String) newEvent.get("type"));
                if (((String) newEvent.get("type")).equals("Passive")) {
                    storeEvent = new NewsEvent(NewsType.PASSIVE);
                    isPassive=true;
                } else if (((String) newEvent.get("type")).equals("Active") &&
						   ((String) newEvent.get("nation")).equals(playerNation.getName())) {
                    storeEvent = new NewsEvent(NewsType.ACTIVE);

                } else {
                    storeEvent = new NewsEvent(NewsType.INTER);
                    isPassive=true;
                }
                storeEvent.setIndex(Integer.parseInt((String) newEvent.get("pid")));
                storeEvent.setDescription((String) newEvent.get("desc"));
                storeEvent.setNation((String) newEvent.get("nation"));

				JSONObject newConseq = (JSONObject) newEvent.get("consequences");
				if (newConseq.containsKey("political")) {
					Double value = new Double(newConseq.get("political").toString());
					storeEvent.addYConseq(ConseqType(Consequence.POLI, value));
				}
				if (newConseq.containsKey("wallet")) {
					Double value = new Double(newConseq.get("wallet").toString());
					storeEvent.addYConseq(ConseqType(Consequence.WALLET, value));
				} 
				if (newConseq.containsKey("ghg")) {
					Double value = new Double(newConseq.get("ghg").toString());
					storeEvent.addYConseq(ConseqType(Consequence.GHG, value));
				}
				if (newConseq.containsKey("temp")) {
					Double value = new Double(newConseq.get("temp").toString());
					storeEvent.addYConseq(ConseqType(Consequence.TEMP, value));
				}
				if (newConseq.containsKey("percip")) {
					Double value = new Double(newConseq.get("percip").toString());
					storeEvent.addYConseq(ConseqType(Consequence.PERCIP, value));
				}

				

				JSONObject newRepur = (JSONObject) newEvent.get("repercussions");
				if (newRepur != null) {
					if (newRepur.containsKey("political")) {
						Double value = new Double(newRepur.get("political").toString());
						storeEvent.addNConseq(ConseqType(Consequence.POLI, value));
					}
					if (newRepur.containsKey("wallet")) {
						Double value = new Double(newRepur.get("wallet").toString());
						storeEvent.addNConseq(ConseqType(Consequence.WALLET, value));
					} 
					if (newRepur.containsKey("ghg")) {
						Double value = new Double(newRepur.get("ghg").toString());
						storeEvent.addNConseq(ConseqType(Consequence.GHG, value));
					}
					if (newRepur.containsKey("temp")) {
						Double value = new Double(newRepur.get("temp").toString());
						storeEvent.addNConseq(ConseqType(Consequence.TEMP, value));
					}
					if (newRepur.containsKey("percip")) {
						Double value = new Double(newRepur.get("percip").toString());
						storeEvent.addNConseq(ConseqType(Consequence.PERCIP, value));
                    }
				}

                if(isPassive) {
                    WorldEvents.add(storeEvent);
                }else{
                    PlayerEvents.add(storeEvent);
                }

            }
            for(NewsEvent ep: PlayerEvents){
            	System.out.println(ep.getDescription()+", "+ep.getIndex()+", "+ep.getType()+", ");
            	for(ConseqType c : ep.getYConseq()){
            		System.out.println(c.getValue());
            	}
            	System.out.println(" ");
            	for(ConseqType c : ep.getNConseq()){
            		System.out.println(c.getValue());
            	}
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void sortEvents() {
        Collections.sort(WorldEvents, new indexComparator());
        Collections.sort(PlayerEvents, new indexComparator());
    }
    public NewsEvent triggerPlayerEvents(){
        NewsEvent triggerPE=PlayerEvents.getFirst();
        PlayerEvents.removeFirst();
        PlayerEvents.offerLast(triggerPE);
        return triggerPE;
    }
    public NewsEvent triggerWorldEvents(){
        NewsEvent triggerWE= WorldEvents.getFirst();
        WorldEvents.removeFirst();
        WorldEvents.offerLast(triggerWE);
        return triggerWE;
    }

}
