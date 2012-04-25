import java.awt.*;
import java.awt.event.*;

/** This is the main class. It's important for initializing our frame called Clocks.
 * It allows us to see a window and to implement our subclasses to view the 
 * clocks as well as adding listeners to the window.
 * @author Erick Mikoshi
 *
 **/
public class Main {

	public static void main(String[] args) {
        final Frame ourApp = new Frame("Clocks");
        ourApp.addWindowListener(new Closer());

        ourApp.setLocation(300, 80);
        /*
         * This creates a new instance of each clock within the frame, but does not
         * call them until later on when used with use of the ActionListeners.
         * At first the canvas 'Default' is added to prompt the user.
         */
        final Default da1 = new Default();
        final Analog da2 = new Analog();
        final Digital da3 = new Digital();
        final Binary da4 = new Binary();
        ourApp.add(da1);       
        /*
         * This adds the menubar to the frame, it is necessary in order to view what
         * clocks are available under the submenu. Action listeners are added to the
         * submenu items so the clocks will be shown when clicked on. 'Removeall' erases
         * whatever previous canvas was there, 'add' adds the canvas to the Frame, and
         * 'pack' resizes the frame to the size specified in the canvas.
         */
        MenuBar mb = new MenuBar();
        ourApp.setMenuBar(mb);
        Menu m = new Menu("Clocks");
        mb.add(m);     
        
        MenuItem m1 = new MenuItem("Analog");
        m.add(m1);
        m1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		   ourApp.removeAll();
                   ourApp.add(da2); 
                   ourApp.pack();
        	}
     });
        
        MenuItem m2 = new MenuItem("Digital");
        m.add(m2);
        m2.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
          		ourApp.removeAll();
        		ourApp.add(da3);
        		ourApp.pack();
        	}   	
      });
        
        MenuItem m3 = new MenuItem("Binary");
        m.add(m3);
        m3.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		ourApp.removeAll();
          		ourApp.add(da4);
          		ourApp.pack();
        	}
        });
        
        ourApp.pack();
        ourApp.setVisible(true);
        /*
         * This while loop is necessary to continuously call the try and catch
         * statement. Within this each of the clocks are repainted so they can
         * continuously update with the current time. Lastly, sleep(1000) has it
         * referring to how often it will be repainted (1000ms = once a second).
         */
        while(true){
       
        	da2.repaint(); 
        	da3.repaint();
        	da4.repaint();
            try { 
                Thread.sleep(1000); 
            } catch (InterruptedException ie) {}
      }
	}
	
}

