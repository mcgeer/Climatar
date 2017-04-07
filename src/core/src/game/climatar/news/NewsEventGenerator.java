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
    private static LinkedList<NewsEvent> WorldEvents;
    private static LinkedList<NewsEvent> PlayerEvents;

    public NewsEventGenerator(GameState gs) {
        playerNation = (Nation) gs.get(WorldProperty.NATION.id());
        
        readEvents();
        sortEvents();
    }

    private void readEvents() {
        WorldEvents = new LinkedList<NewsEvent>();
        PlayerEvents= new LinkedList<NewsEvent>();
        boolean isPassive;
        JSONParser parser = new JSONParser();
        try {
            JSONArray readArray = (JSONArray) parser.parse(Gdx.files.internal("Events.json").readString());
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
                ConseqType storeConseq = ConseqType.DEF;

                for (Object elm : (JSONArray) newEvent.get("consequences")) {
                    JSONObject newConseq = (JSONObject) elm;
                    if (newConseq.containsKey("political")) {
                        storeConseq = ConseqType.POLI;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("political")));
                    }
                    if (newConseq.containsKey("wallet")) {
                        storeConseq = ConseqType.WALLET;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("wallet")));
                    } 
                    if (newConseq.containsKey("ghg")) {
                        storeConseq = ConseqType.GHG;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("ghg")));
                    }
                    if (newConseq.containsKey("temp")) {
                        storeConseq = ConseqType.TEMP;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("temp")));
                    }
                    if (newConseq.containsKey("percip")) {
                        storeConseq = ConseqType.PERCIP;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("percip")));
                    }
                    storeEvent.addYConseq(storeConseq);

                }
                for (Object elm : (JSONArray) newEvent.get("repercussions")) {
                    JSONObject newConseq = (JSONObject) elm;
                    if (newConseq.containsKey("political")) {
                        storeConseq = ConseqType.POLI;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("political")));
                    }
                    if (newConseq.containsKey("wallet")) {
                        storeConseq = ConseqType.WALLET;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("wallet")));
                    } 
                    if (newConseq.containsKey("ghg")) {
                        storeConseq = ConseqType.GHG;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("ghg")));
                    }
                    if (newConseq.containsKey("temp")) {
                        storeConseq = ConseqType.TEMP;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("temp")));
                    }
                    if (newConseq.containsKey("percip")) {
                        storeConseq = ConseqType.PERCIP;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("percip")));
                    }
                    storeEvent.addNConseq(storeConseq);

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

    public static void sortEvents() {
        Collections.sort(WorldEvents, new indexComparator());
        Collections.sort(PlayerEvents, new indexComparator());
    }
    public NewsEvent triggerPlayerEvents(){
        NewsEvent triggerPE=PlayerEvents.removeFirst();
        PlayerEvents.offerLast(triggerPE);
        return triggerPE;
    }
    public NewsEvent triggerWorldEvents(){
        NewsEvent triggerWE= WorldEvents.removeFirst();
        WorldEvents.offerLast(triggerWE);
        return triggerWE;
    }

}