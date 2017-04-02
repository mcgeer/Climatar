package game.climatar.news;
import java.util.ArrayList;
import java.util.List;

public enum EventType {
	
	PASSIVE(1),
	ACTIVE(2),
	INTER(3);
	private static List <?> yCon;
	private static List <?> nCon;
	
	EventType (int num){
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
	
}
