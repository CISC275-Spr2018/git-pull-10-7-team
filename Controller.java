import java.awt.EventQueue;
import javax.swing.Timer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller{

    private Model model;
    private View view;
    final int drawDelay = 30; //msec


    Action drawOrc;

    
    public Controller() {
    	view = new View();
    	model = new Model(view.getWidth(), view.getHeight()  - (view.getButtonHeight() * 3), view.getImageWidth(), view.getImageHeight());
    	drawOrc = new AbstractAction() {
    		public void actionPerformed(ActionEvent e) {
    			animate();
    		}
    	};
	
    }
	
    //run the simulation
    public void animate() {
    	if(view.isUpdating()){
    		//increment the x and y coordinates, alter direction if necessary
    		model.updateLocationAndDirection(view.isLeftCBSelected(), view.isRightCBSelected(), view.isUpCBSelected(), view.isDownCBSelected());
    		//update the view
    		view.update(model.getX(), model.getY(), model.getDirect());
    	}
    }

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {

		@Override
		public void run() {
			Controller controller = new Controller();
		    Timer t = new Timer(controller.drawDelay, controller.drawOrc);
		    t.start();
			
		}
	});
    }

}

