package game.climatar.news;
import java.util.ArrayList;
import java.util.List;

import game.climatar.map.Nation;

public class NewsEvent {
	
	enum NewsType {
		PASSIVE,
		ACTIVE,
		INTER;
	}
	
	private NewsType type;
	private  List <ConseqType> yCon;
	private  List <ConseqType> nCon;
	
	public NewsEvent (NewsType type){
		if (type==NewsType.PASSIVE){
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
	private Integer pid;
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
	public void setIndex(int p){
		pid=p;
	}

	public void addYConseq(ConseqType T){
		yCon.add(T);
	}
	public void addNConseq(ConseqType T){
		nCon.add(T);
	}
	public Integer getIndex(){
		return pid;
	}
	private static Nation getEventNation(){
		return rep;
	}

	public NewsType getType(){
		return type;
	}

	public List<ConseqType> getYConseq(){
		return yCon;
	}
	public List<ConseqType> getNConseq(){
		return nCon;
	}
 
	
	
	
	
}

