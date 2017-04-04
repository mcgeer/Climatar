package game.climatar.news;
import com.badlogic.gdx.Gdx;

import game.climatar.map.Nation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class NewsEventGenerator {
    private  Nation playerNation;

    NewsEventGenerator(Nation np){
        playerNation=np;
    }
    public void readEvents(){
        JSONParser parser= new JSONParser();
    try{
        JSONArray readArray=(JSONArray)parser.parse(Gdx.files.internal("Events.json").readString());
       for(Object obj: readArray){
           JSONObject newEvent =( JSONObject) obj;
            NewsEvents storeEvent;
           if((String)newEvent.get("type")=="Passive"){
               storeEvent= NewsEvents.PASSIVE;
           }
           else if ((String)newEvent.get("type")=="Active" && (String)newEvent.get("nation")==playerNation.getName()){
                storeEvent= NewsEvents.ACTIVE;

           }
           else{
               storeEvent= NewsEvents.INTER;
           }
            storeEvent.setDescription((String)newEvent.get("desc"));
            storeEvent.setNation((String)newEvent.get("nation"));


           
       } }catch (ParseException e) {
            e.printStackTrace();
        }



    }
    }

