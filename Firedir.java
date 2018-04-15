
public enum Firedir {

	FIRENORTH("fire_north"),
	FIRENORTHEAST("fire_northeast"),
	FIREEAST("fire_east"),
	FIRESOUTHEAST("fire_southeast"),
	FIRESOUTH("fire_south"),
	FIRESOUTHWEST("fire_southwest"),
	FIREWEST("fire_west"),
	FIRENORTHWEST("fire_northwest");
	
	private String name = null;
	
	private Firedir(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}
}
