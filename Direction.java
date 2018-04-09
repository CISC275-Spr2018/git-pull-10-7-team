

public enum Direction {

	NORTH("forward_north"),
	NORTHEAST("forward_northeast"),
	EAST("forward_east"),
	SOUTHEAST("forward_southeast"),
	SOUTH("forward_south"),
	SOUTHWEST("forward_southwest"),
	WEST("forward_west"),
	NORTHWEST("forward_northwest");
	
	
	private String name = null;
	
	private Direction(String s){
		name = s;
	}
	public String getName() {
		return name;
	}


}
