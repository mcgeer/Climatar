package game.climatar.news;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public enum ConseqType {
	
	POLI("political"),
    WALLET("wallet"),
    GHG("ghg"),
    TEMP("temp"),
    PERCIP("percip"),
	DEF("Default");

	private String name;
	private int valueChanged;
	
	ConseqType (String s){
		name=s;
	}

	public void addValue(int v){
		valueChanged=v;
	}
	
}
