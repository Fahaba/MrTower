package al.artofsoul.data.pllaka;

public enum PllakaType {
	
	GRASS("res/tiles/bari32", true), DIRT("res/tiles/toka32", false), WATER("res/tiles/uj32", false), NULL("res/tiles/uj32", false);
	
	String textureName;
	boolean buildable;
	
	PllakaType(String textureName, boolean buildable){
		this.textureName = textureName;
		this.buildable = buildable;
	}
}
