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

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class View extends JPanel {

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
    int xloc;
    int yloc;
    int hold_x;
    int hold_y;
    boolean isUpdating = true;

    BufferedImage[][] pics = new BufferedImage[pngCount][frameCount];//[dir][frame]

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
    }

    @Override
    public void paint(Graphics g) {
    	picNum += 1;
    	g.drawImage(pics[heading.ordinal()][picNum % frameCount], xloc, yloc, Color.gray, this);
    }

    public void update(int xloc, int yloc, Direction dir) {
    	this.heading = dir;
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
}
