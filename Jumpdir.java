public enum Jumpdir {

	JUMPNORTH("jump_north"),
	JUMPNORTHEAST("jump_northeast"),
	JUMPEAST("jump_east"),
	JUMPSOUTHEAST("jump_southeast"),
	JUMPSOUTH("jump_south"),
	JUMPSOUTHWEST("jump_southwest"),
	JUMPWEST("jump_west"),
	JUMPNORTHWEST("jump_northwest");
	
	private String name = null;
	
	private Jumpdir(String s) {
	    name = s;
	}
	
	public String getName() {
	    return name;
	}

}
