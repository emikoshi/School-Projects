
package game;

import java.awt.Frame;

public class Main { 

	

  
    public static void main(String[] args) {
        Frame ourApp = new Frame("Paddle Game");
        ourApp.addWindowListener(new Closer());
        
        BallPanel drawarea = new BallPanel();
        ourApp.add(drawarea);
        
        PlayerMouse clicker = new PlayerMouse(drawarea);
        drawarea.addMouseMotionListener(clicker);
        
        ourApp.pack();
        ourApp.setVisible(true);
        
        
        while (true) {
        	
        
            try { 
                Thread.sleep(10); 
            } catch (InterruptedException ie) {}
            
           
            drawarea.timestep();
            drawarea.repaint();
           
        }
      }
   }


