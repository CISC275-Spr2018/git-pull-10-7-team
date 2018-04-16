/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * [x] provide boundaries
 * use proper images for direction
 * [x] load images for all direction (an image should only be loaded once!!! why?)
 **/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class View extends JPanel{

    static JFrame frame;
    static JFrame b_frame;
    static JButton button;
    static JCheckBox rightCB;
    static JCheckBox leftCB;
    static JCheckBox upCB;
    static JCheckBox downCB;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    final int frameCount = 10;
    final int pngCount = 8;
    final int fireFC = 4;
    int fcNum = 0;
    static int buttonWidth = 120;
    static int buttonHeight = 30;
    static int panelHeight = 40;
    static int button_xLoc = 0;
    static int button_yLoc = 360;
    static int frameHeight = 400 + (panelHeight);
    static int frameWidth = 600;
    int picNum = 0;
    Direction heading;
    Direction heading_hold;
    Firedir fireheading;
    Jumpdir jumpheading;
    int xloc;
    int yloc;
    int hold_x;
    int hold_y;
    boolean isUpdating = true;
    boolean isFiring = false;
    boolean isJumping = false;
    
    BufferedImage[][] pics = new BufferedImage[pngCount][frameCount];//[dir][frame]
    BufferedImage[][] firepics = new BufferedImage[pngCount][fireFC];
    BufferedImage[][] jumppics = new BufferedImage[pngCount][fireFC];
    
    //set dimensions and load all images on init
    public View() {
    	loadImages();

    	frame = new JFrame();
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	
    	frame.setVisible(true);
    	System.out.println("initialized View");

    	JPanel panel = new JPanel();

    	panel.setBounds(button_xLoc, button_yLoc, frameWidth, buttonHeight * 2);

    	frame.add(panel);

    	button = new JButton("Start/Stop");

    	button.setPreferredSize(new Dimension(buttonWidth, 30));

    	frame.setLayout(null);

    	
    	button.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			isUpdating = !isUpdating;
    			
    		}
    	});
	
    	panel.add(button);

    	//CheckBox stuff
    	leftCB = new JCheckBox("Left");
    	leftCB.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			if(rightCB.isSelected())
    				rightCB.setSelected(false);
    		}
    	});
    	
    	rightCB = new JCheckBox("Right");
    	rightCB.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			if(leftCB.isSelected())
    				leftCB.setSelected(false);
    		}
    	});

    	upCB = new JCheckBox("Up");
    	upCB.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			if(downCB.isSelected())
    				downCB.setSelected(false);
    		}
    	});
    	
    	downCB = new JCheckBox("Down");
    	downCB.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0) {
    			if(upCB.isSelected())
    				upCB.setSelected(false);
    		}
    	});

    	panel.add(leftCB);
    	panel.add(rightCB);
    	panel.add(upCB);
    	panel.add(downCB);
    	
    	//Keyboard Input
    	//Left Arrow
    	panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftPressed");
    	panel.getActionMap().put("leftPressed", new AbstractAction(){
    		@Override
			public void actionPerformed(ActionEvent arg0) {
				if(rightCB.isSelected()){
					rightCB.setSelected(false);
				}
				leftCB.setSelected(true);
			}
    	});
    	
    	panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "leftReleased");
    	panel.getActionMap().put("leftReleased", new AbstractAction(){
    		@Override
			public void actionPerformed(ActionEvent arg0) {
				leftCB.setSelected(false);
			}
    	});
    	
    	//Right Arrow
    	panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightPressed");
    	panel.getActionMap().put("rightPressed", new AbstractAction(){
    		@Override
			public void actionPerformed(ActionEvent arg0) {
				if(leftCB.isSelected()){
					leftCB.setSelected(false);
				}
				rightCB.setSelected(true);
			}
    	});
    	
    	panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "rightReleased");
    	panel.getActionMap().put("rightReleased", new AbstractAction(){
    		@Override
			public void actionPerformed(ActionEvent arg0) {
				rightCB.setSelected(false);
			}
    	});
    	
    	//Up Arrow
    	panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "upPressed");
    	panel.getActionMap().put("upPressed", new AbstractAction(){
    		@Override
			public void actionPerformed(ActionEvent arg0) {
				if(downCB.isSelected()){
					downCB.setSelected(false);
				}
				upCB.setSelected(true);
			}
    	});
    	
    	panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "upReleased");
    	panel.getActionMap().put("upReleased", new AbstractAction(){
    		@Override
			public void actionPerformed(ActionEvent arg0) {
				upCB.setSelected(false);
			}
    	});
    	
    	//Down Arrow
    	panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
    	panel.getActionMap().put("downPressed", new AbstractAction(){
    		@Override
			public void actionPerformed(ActionEvent arg0) {
				if(upCB.isSelected()){
					upCB.setSelected(false);
				}
				downCB.setSelected(true);
			}
    	});
    	
    	panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "downReleased");
    	panel.getActionMap().put("downReleased", new AbstractAction(){
    		@Override
			public void actionPerformed(ActionEvent arg0) {
				downCB.setSelected(false);
			}
    	});
    	
    	//Fire Key (F)
    	panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('f'), "Fire");
    	panel.getActionMap().put("Fire", new AbstractAction(){
    		@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!isFiring)
					isFiring = true;
			}
    	});

	//Jump Key (J)
    	panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('j'), "Jump");
    	panel.getActionMap().put("Jump", new AbstractAction(){
    		@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!isJumping)
					isJumping = true;
			}
    	});
	
    	
    }
    
    @Override
    public void paint(Graphics g) {
    	picNum += 1;
    	if (!isFiring && !isJumping) {
	    g.drawImage(pics[heading.ordinal()][picNum % frameCount], xloc, yloc, Color.gray, this);
    	}
	else if (!isFiring) {
	    g.drawImage(jumppics[fireheading.ordinal()][fcNum++ % fireFC], xloc, yloc, Color.gray, this);
	    if(fcNum % fireFC == 0)
		isJumping = false;
	}
    	else {
	    g.drawImage(firepics[fireheading.ordinal()][fcNum++ % fireFC], xloc, yloc, Color.gray, this);
	    if(fcNum % fireFC == 0)
		isFiring = false;
    	}
    }

    public void update(int xloc, int yloc, Direction dir, Firedir firedir, Jumpdir jumpdir) {
    	this.heading = dir;
    	this.fireheading = firedir;
	this.jumpheading = jumpdir;
    	this.xloc = xloc;
    	this.yloc = yloc;
    	this.frame.repaint();
    }

    private void loadImages() {
    	//for each direction
    	for (Direction dir : Direction.values()) {	    
    		BufferedImage img = createImage(dir.getName());
    		pics[dir.ordinal()] = new BufferedImage[10];

    		for(int i = 0; i < frameCount; i++)
    			pics[dir.ordinal()][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	}
    	for (Firedir fdir : Firedir.values()) {
    		BufferedImage img = createImage(fdir.getName());
    		firepics[fdir.ordinal()] = new BufferedImage[4];
    		
    		for (int i = 0; i < fireFC; i++) {
    			firepics[fdir.ordinal()][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		}
    	}
	for (Jumpdir jdir : Jumpdir.values()) {
    		BufferedImage img = createImage(jdir.getName());
    		jumppics[jdir.ordinal()] = new BufferedImage[4];
    		
    		for (int i = 0; i < fireFC; i++) {
    			jumppics[jdir.ordinal()][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		}
    	}
    }

    private BufferedImage createImage(String dir) {
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File("./images/orc/orc_" + dir + ".png"));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    public int getHeight() {
    	return frameHeight;
    }
    public int getWidth() {
    	return frameWidth;
    }
    public int getImageWidth() {
    	return imgWidth;
    }
    public int getImageHeight() {
    	return imgHeight;
    }
    public int getButtonHeight() {
    	return buttonHeight;
    }
    
    public boolean isUpdating(){
    	return isUpdating;
    }
    public boolean isLeftCBSelected(){
    	return leftCB.isSelected();
    }
    public boolean isRightCBSelected(){
    	return rightCB.isSelected();
    }
    public boolean isUpCBSelected(){
    	return upCB.isSelected();
    }
    public boolean isDownCBSelected(){
    	return downCB.isSelected();
    }
    public boolean isFiring(){
    	return isFiring;
    }
    public boolean isJumping(){
	return isJumping;
    }
}
