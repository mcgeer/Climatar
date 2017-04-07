package game.climatar.map;

public enum Nation {
	FIRE("Fire"),
	WATER("Water"),
	AIR("Air"),
	EARTH("Earth"),
	BLUE_LOTUS("UN");

	private String name;
	 Nation(String s){
		name=s;
	}
	public String getName(){
		return name;
	}
}
