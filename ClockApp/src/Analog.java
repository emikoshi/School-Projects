import java.awt.*;
import java.util.*;

/** This is the class for the Analog Clock. It's necessary to initialize
 * the Analog Clock component and its respective graphics.
 * It draws the Analog Clock in the given canvas and allows it to be 
 * seen. It also incorporates the right functions to run it successfully.
 * @author Erick Mikoshi
 *
 **/

public class Analog extends Canvas{
	/*
	 * These are all different declarations for certain variables, all that
	 * exist within the Analog class. Diameter is used for the height and width
	 * of the clock. centerX is used for the x coordinate of the middle of the
	 * clock. centerY is used for the y coordinate of the middle of the clock.
	 */
	private Font font = new Font("Helvetica", Font.BOLD, 40);
	private static final long serialVersionUID = 1L;
	double two_pi = 2.0*Math.PI;
	int diameter, centerX, centerY, hours, seconds, minutes, millis;	
	/*
	 * Sets what the size of the canvas will be when Analog is open.
	 */
	public Analog()
    {
	setSize(480, 480);
    } 
	
	@Override
	public void paint(Graphics g) {
		/*
		 * This paints the background images for the clock and the original background.
		 */
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.YELLOW);
        g.fillOval(0, 0, diameter, diameter);
		
        /*
         * This is used to get the current dimensions of the panel,
         * since it may have gotten resized by the user.
         */
		int w = getWidth();
		int h = getHeight();
		diameter = ((w < h) ? w : h);
		centerX = diameter / 2;
	    centerY = diameter / 2;
	    /*
	     * This draws the clock hands dynamically each time.
	     */
        drawClockHands(g);
        
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, diameter, diameter);

        int radius = diameter / 2;        
     
        /*
         * This for-statement draws the tick marks around the circumference.
         */
        for (int sec = 0; sec < 60; sec++) {
            int tickStart;
            if (sec%5 == 0) {
                tickStart = radius - 10;  
            } else {
                tickStart = radius - 5;   
            }
            g.setColor(Color.black);
            drawRadius(g, sec/60.0, tickStart , radius);
        }   
   }  
	
	public void drawClockHands(Graphics g) {
		/*
		 *  These variables are assigned to get the 
		 *  various time elements from the Calendar, which is an object.
		 */
		Calendar now = Calendar.getInstance();
        hours   = now.get(Calendar.HOUR);
        minutes = now.get(Calendar.MINUTE);
        seconds = now.get(Calendar.SECOND);
        millis  = now.get(Calendar.MILLISECOND);

        /*
         * Initiates and draws the second hand.
         */
        int handMax = diameter / 2;  
        double fseconds = (seconds + (double)millis/1000) / 60.0 ;
        g.setColor(Color.RED);
        drawRadius(g, fseconds, 0, handMax);
            
        /*
         * Initiates and draws the minute hand.
         */
        handMax = diameter / 3; 
        double fminutes = (minutes + fseconds) / 60.0;
        g.setColor(Color.BLACK);
        drawRadius(g, fminutes, 0, handMax);
        
        /*
         * Initiates and draws the hour hand.
         */
        handMax = diameter / 4;
        drawRadius(g, (hours + fminutes) / 12.0, 0, handMax);

        g.setFont(font);
        g.drawString("Analog", 175, 140);
    }
	
   /*
    *  This draw lines along a radius from the clock face center.
    *  Since the parameters/inputs are can be changed, it can be utilized 
    *  to draw tick marks and the hands.
    * 
    */
	public  void drawRadius(Graphics g, double percent,
            int minRadius, int maxRadius) {
			double radians = (0.5 - percent) * two_pi;
			double sine   = Math.sin(radians);
			double cosine = Math.cos(radians);

			int dxmin = centerX + (int)(minRadius * sine);
			int dymin = centerY + (int)(minRadius * cosine);		
			int dxmax = centerX + (int)(maxRadius * sine);
			int dymax = centerY + (int)(maxRadius * cosine);
			g.drawLine(dxmin, dymin, dxmax, dymax);
				
	}
}
