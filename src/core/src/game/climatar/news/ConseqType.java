package game.climatar.news;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class ConseqType {

	public enum Consequence {
	
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

	private Consequence consequence;
	private double value;
	
	ConseqType (Consequence consequence, double value){
		this.consequence = consequence;
		this.value = value;
	}

	public double getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	public Consequence getConsequence() {
		return consequence;
	}
}
	

