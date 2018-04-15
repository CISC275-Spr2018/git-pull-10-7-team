/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 * detect collision with boundaries
 * decide next direction
 * [x] provide direction
 * provide location
 **/

public class Model {

    static int xIncr = 4;
    static int yIncr = 4;
    static int xloc = 0;
    static int yloc = 0;
    static int frameWidth;
    static int frameHeight;
    static int charWidth;
    static int charHeight;
    boolean right;
    boolean down;
    boolean xMoving;
    boolean yMoving;
    Direction dir = Direction.SOUTHEAST;
    Firedir firedir = Firedir.FIRESOUTHWEST;

    public Model(int width, int height, int imgWidth, int imgHeight) {
    	this.frameWidth = width;
    	this.frameHeight = height;
    	this.charWidth = imgWidth;
    	this.charHeight = imgHeight;
    	
    	System.out.println("initialized Model");
    }


    public void updateLocationAndDirection(boolean lBtn, boolean rBtn, boolean uBtn, boolean dBtn) {
    	try {
    		Thread.sleep(30);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	
    	if(frameWidth - xloc - charWidth < 0 && rBtn){
    		right = false;
    		xMoving = false;
    	}
    	else if(xloc < 0 && lBtn){
    		right = true;
    		xMoving = false;
    	}
    	else{
        	right = rBtn ? true : false;
        	xMoving = rBtn || lBtn ? true : false;
    	}
    	
    	if(frameHeight - yloc - charHeight < 0 && dBtn){
    		down = false;
    		yMoving = false;
    	}
    	else if(yloc < 0 && uBtn){
    		down = true;
    		yMoving = false;
    	}
    	else{
        	down = dBtn ? true : false;
        	yMoving = dBtn || uBtn ? true : false;
    	}
    	
    	if(xMoving)
    		xloc += right ? xIncr : -xIncr;
    	if(yMoving)
    		yloc += down ? yIncr : -yIncr;
    }

    
    //returns ordinal value of the direction according to the Direction enum
    public Direction getDirect() {
    	if(xMoving && yMoving){
    		if(right && down)
    			dir = Direction.SOUTHEAST;
    		else if(right && !down)
    			dir = Direction.NORTHEAST;
    		else if(!right && down)
    			dir = Direction.SOUTHWEST;
    		else
    			dir = Direction.NORTHWEST;
    	}
    	else if(xMoving){
    		dir = right ? Direction.EAST : Direction.WEST;
    	}
    	else if(yMoving){
    		dir = down ? Direction.SOUTH : Direction.NORTH;
    	}
    	return dir;
    }   
    
    public Firedir getFireDirect() {
    	if(xMoving && yMoving){
    		if(right && down)
    			firedir = Firedir.FIRESOUTHEAST;
    		else if(right && !down)
    			firedir = Firedir.FIRENORTHEAST;
    		else if(!right && down)
    			firedir = Firedir.FIRESOUTHWEST;
    		else
    			firedir = Firedir.FIRENORTHWEST;
    	}
    	else if(xMoving){
    		firedir = right ? Firedir.FIREEAST : Firedir.FIREWEST;
    	}
    	else if(yMoving){
    		firedir = down ? Firedir.FIRESOUTH : Firedir.FIRENORTH;
    	}
    	return firedir;
    } 

    public int getX() {
    	return this.xloc;
    }
    public int getY() {
    	return this.yloc;
    }
}
