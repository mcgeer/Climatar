package game.climatar.news;
import java.util.ArrayList;
import java.util.List;

import game.climatar.map.Nation;

public enum NewsEvents {
	PASSIVE(1),
	ACTIVE(2),
	INTER(3);

	private static List <?> yCon;
	private static List <?> nCon;
	NewsEvents (int num){
		if (num==1){
			List <?> yCon= new ArrayList<Object>();
			List <?> nCon=null;
		}
		else if (num==2){
			List <?> yCon= new ArrayList<Object>();
			List <?> nCon= new ArrayList<Object>();
		}
		else if (num==3){
			List <?> yCon= new ArrayList<Object>();
			List <?> nCon= null;
		}
	}
	private String BaseEvent;
	private static Nation rep;
	
	public void setDescription(String s){
		BaseEvent=s;
	}
	public void setNation(String s){
			if(s=="FIRE"){
			rep=Nation.FIRE;
		}
		else if(s=="WATER"){
			rep=Nation.WATER;
		}
		else if(s=="AIR"){
			rep=Nation.AIR;
		}
		else{
			rep=Nation.EARTH;
		}
	}
	
	private static Nation getEventNation(){
		return rep;
	}
	
	private static void newsExecute(){
		
	}

	
	
	
}

