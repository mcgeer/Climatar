package game.climatar.map;

public enum Nation {
	FIRE("FIRE", "fire.png"), 
	WATER("WATER", "water.png"), 
	AIR("AIR", "air.png"), 
	EARTH("EARTH", "earth.png");

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
