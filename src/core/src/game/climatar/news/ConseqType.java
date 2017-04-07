package game.climatar.news;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
public class ConseqType{

	enum Consequence {
	
	POLI("political"),
    WALLET("wallet"),
    GHG("ghg"),
    TEMP("temp"),
    PERCIP("percip"),
	DEF("Default");
		private String name;
		Consequence(String s){
		name=s;
			
	}
	}

	private Consequence con;
	
	private double valueChanged;
	
	ConseqType (Consequence c){
		con=c;
	}

	public void addValue(double v){
		valueChanged=v;
	}

	public double  getValue() {
		// TODO Auto-generated method stub
		return valueChanged;
	}
}
	

