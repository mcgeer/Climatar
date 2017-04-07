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
                if ((String) newEvent.get("type") == "Passive") {
                    storeEvent = new NewsEvent(NewsType.PASSIVE);
                    isPassive=true;
                } else if ((String) newEvent.get("type") == "Active" && (String) newEvent.get("nation") == playerNation.getName()) {
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
                    ConseqType storeConseq = ConseqType.POLI;
					storeConseq.addValue(new Double(newConseq.get("political").toString()));
                    storeEvent.addYConseq(storeConseq);
				}
				if (newConseq.containsKey("wallet")) {
                    ConseqType storeConseq = ConseqType.WALLET;
					storeConseq.addValue(new Double(newConseq.get("wallet").toString()));
                    storeEvent.addYConseq(storeConseq);
				} 
				if (newConseq.containsKey("ghg")) {
                    ConseqType storeConseq = ConseqType.GHG;
					storeConseq.addValue(new Double(newConseq.get("ghg").toString()));
                    storeEvent.addYConseq(storeConseq);
				}
				if (newConseq.containsKey("temp")) {
                    ConseqType storeConseq = ConseqType.TEMP;
					storeConseq.addValue(new Double(newConseq.get("temp").toString()));
                    storeEvent.addYConseq(storeConseq);
				}
				if (newConseq.containsKey("percip")) {
                    ConseqType storeConseq = ConseqType.PERCIP;
					storeConseq.addValue(new Double(newConseq.get("percip").toString()));
                    storeEvent.addYConseq(storeConseq);
				}



				JSONObject newRepur = (JSONObject) newEvent.get("repercussions");
				if (newRepur != null) {
					if (newRepur.containsKey("political")) {
                        ConseqType storeRepur = ConseqType.POLI;
						storeRepur.addValue(new Double(newRepur.get("political").toString()));
                        storeEvent.addNConseq(storeRepur);
					}
					if (newRepur.containsKey("wallet")) {
                        ConseqType storeRepur = ConseqType.WALLET;
						storeRepur.addValue(new Double(newRepur.get("wallet").toString()));
                        storeEvent.addNConseq(storeRepur);
					} 
					if (newRepur.containsKey("ghg")) {
                        ConseqType storeRepur = ConseqType.GHG;
						storeRepur.addValue(new Double(newRepur.get("ghg").toString()));
                        storeEvent.addNConseq(storeRepur);
					}
					if (newRepur.containsKey("temp")) {
                        ConseqType storeRepur = ConseqType.TEMP;
						storeRepur.addValue(new Double(newRepur.get("temp").toString()));
                        storeEvent.addNConseq(storeRepur);
					}
					if (newRepur.containsKey("percip")) {
                        ConseqType storeRepur = ConseqType.PERCIP;
						storeRepur.addValue(new Double(newRepur.get("percip").toString()));
                        storeEvent.addNConseq(storeRepur);
                    }

				}


                if(isPassive) {
                    WorldEvents.add(storeEvent);
                }else{
                    PlayerEvents.add(storeEvent);
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