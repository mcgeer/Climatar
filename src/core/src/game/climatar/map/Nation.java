package game.climatar.map;

public enum Nation {
	FIRE("Fire"),
	WATER("Water"),
	AIR("Air"),
	EARTH("Earth");

	private String name;
	 Nation(String s){
		name=s;
	}
	public String getName(){
		return name;
	}
	

	
}
