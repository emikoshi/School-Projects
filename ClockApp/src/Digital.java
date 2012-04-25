import java.awt.*;
import java.util.*;

/** This is the class for the Digital Clock. It's necessary to initialize
 * the Digital Clock component and its respective graphics.
 * It draws the Digital Clock in the given canvas and allows it to be 
 * seen. It also incorporates the right functions to run it successfully.
 * The clock is shown in a 24 hour format.
 * @author Erick Mikoshi
 *
 **/
public class Digital extends Canvas{
	private Font font = new Font("Helvetica", Font.BOLD, 40);
	
	private static final long serialVersionUID = 1L;
	
		/*
		 * Sets what the size of the canvas will be when Analog is open.
		 */
	 	public Digital()
	    {
	        setSize(480, 480);   
	    }  
	 	
     	public void paint(Graphics g) 
     	{	
     		/*
    		 * This paints the background images for the clock and the original background.
    		 */
     		g.setColor(Color.GRAY);
     		g.fillRect(0,0,getWidth(),getHeight());
     		g.setColor(Color.BLACK);
     		g.setFont(font);
     		/*
    	     * This draws the Digital clock dynamically each time.
    	     */
     		String string = timeNow();          
     		g.drawString(string,160,230);
     		g.drawString("Digital", 175, 100);
        }     
     	
     	/*
		 *  This function is used to get the current time from the system
		 *  The variables are assigned to get the various time elements from 
		 *  the Calendar, which is an object and appends them to a string.
		 */
     	public String timeNow()
     	{
     		Calendar now = Calendar.getInstance();
     		int hrs = now.get(Calendar.HOUR_OF_DAY);
     		int min = now.get(Calendar.MINUTE);
     		int sec = now.get(Calendar.SECOND);
     		     		
     		String time = zero(hrs)+":"+zero(min)+":"+zero(sec);
       
     		return time;
     	}
     	
     	/*
     	 * This function simply places a zero in front of the specific variables
     	 * (hours, minutes, and/or seconds) whenever they are less than 10. This way
     	 * the user isn't confused and makes it easier to read.
     	 */
     	public String zero(int num)
          	{
            String number=( num < 10) ? ("0"+num) : (""+num);
            return number;
          	}
  	
}
