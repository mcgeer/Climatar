package game.climatar.map;

public enum Nation {
	FIRE("Fire", "fire.png"), 
	WATER("Water", "water.png"), 
	AIR("Air", "air.png"), 
	EARTH("Earth", "earth.png"), 
	BLUE_LOTUS("UN", "");

	private String name;
	private String imageFileName;

	Nation(String s, String imageFileName) {
		name = s;
		this.imageFileName = imageFileName;
	}

	public String getName() {
		return name;
	}

	public String getImageFileName() {
		return imageFileName;
	}
}
