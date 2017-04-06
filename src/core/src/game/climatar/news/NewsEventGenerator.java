package game.climatar.news;
import com.badlogic.gdx.Gdx;

import game.climatar.map.Nation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class NewsEventGenerator {
    private Nation playerNation;
    private static LinkedList<NewsEvents> PossibleEvents;

    NewsEventGenerator(Nation np) {
        playerNation = np;
    }

    public void readEvents() {
        PossibleEvents = new LinkedList<NewsEvents>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray readArray = (JSONArray) parser.parse(Gdx.files.internal("Events.json").readString());
            for (Object obj : readArray) {
                JSONObject newEvent = (JSONObject) obj;
                NewsEvents storeEvent;
                if ((String) newEvent.get("type") == "Passive") {
                    storeEvent = NewsEvents.PASSIVE;
                } else if ((String) newEvent.get("type") == "Active" && (String) newEvent.get("nation") == playerNation.getName()) {
                    storeEvent = NewsEvents.ACTIVE;

                } else {
                    storeEvent = NewsEvents.INTER;
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
                    } else if (newConseq.containsKey("wallet")) {
                        storeConseq = ConseqType.WALLET;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("wallet")));
                    } else if (newConseq.containsKey("ghg")) {
                        storeConseq = ConseqType.GHG;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("ghg")));
                    } else if (newConseq.containsKey("temp")) {
                        storeConseq = ConseqType.TEMP;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("temp")));
                    } else if (newConseq.containsKey("percip")) {
                        storeConseq = ConseqType.PERCIP;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("percip")));
                    }
                    storeEvent.addYConseq(storeConseq);

                }
                for (Object elm : (JSONArray) newEvent.get("reprecussions")) {
                    JSONObject newConseq = (JSONObject) elm;
                    if (newConseq.containsKey("political")) {
                        storeConseq = ConseqType.POLI;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("political")));
                    } else if (newConseq.containsKey("wallet")) {
                        storeConseq = ConseqType.WALLET;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("wallet")));
                    } else if (newConseq.containsKey("ghg")) {
                        storeConseq = ConseqType.GHG;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("ghg")));
                    } else if (newConseq.containsKey("temp")) {
                        storeConseq = ConseqType.TEMP;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("temp")));
                    } else if (newConseq.containsKey("percip")) {
                        storeConseq = ConseqType.PERCIP;
                        storeConseq.addValue(Integer.parseInt((String) newConseq.get("percip")));
                    }
                    storeEvent.addNConseq(storeConseq);

                }
                PossibleEvents.add(storeEvent);


            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public static void sortEvents() {
        Collections.sort(PossibleEvents, new indexComparator());
    }

}