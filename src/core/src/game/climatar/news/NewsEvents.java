package game.climatar.news;
import java.util.ArrayList;
import java.util.List;

import game.climatar.map.Nation;

public enum NewsEvents {
	PASSIVE(1),
	ACTIVE(2),
	INTER(3);

	private  List <ConseqType> yCon;
	private  List <ConseqType> nCon;
	NewsEvents (int num){
		if (num==1){
			yCon= new ArrayList<ConseqType>();
			nCon=null;
		}
		else {
			 yCon = new ArrayList<ConseqType>();
			 nCon = new ArrayList<ConseqType>();
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

	public void addYConseq(ConseqType T){
		yCon.add(T);
	}
	public void addNConseq(ConseqType T){
		nCon.add(T);
	}
	
	private static Nation getEventNation(){
		return rep;
	}
	
	private static void newsExecute(){
		
	}

	
	
	
}

