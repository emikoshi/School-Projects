import java.awt.*;
import java.util.*;

/** This is the class for the Binary Clock. It's necessary to initialize
 * the Binary Clock component and its respective graphics.
 * It draws the Binary Clock in the given canvas and allows it to be 
 * seen. It also incorporates the right functions to run it successfully.
 * This is in a 24 hour format.
 * @author Erick Mikoshi
 *
 **/

public class Binary extends Canvas{
	/*
	 * Global variables
	 */
    private Font font = new Font("Helvetica", Font.BOLD, 30);

	private static final long serialVersionUID = 1L;
		/*
		 * Sets what the size of the canvas will be when Binary is open.
		 */
	 	public Binary()
	    {
	        setSize(480, 480);
	    }   	
     	/*
     	 * The pain function gets the height and width of the windows to draw and
     	 * fill the background as well as to draw the actual Binary clock.
     	 * @see java.awt.Canvas#paint(java.awt.Graphics)
     	 */
     	public void paint(Graphics g) 
     	{ 		
     		 g.setColor(Color.ORANGE);
     		 g.fillRect(0, 0, getWidth(), getHeight());
     		 g.setFont(font);
     		 g.setColor(Color.BLACK);
     		 g.drawString("Binary", 190, 100);
     		 String  string = timeNow(); 
     		 /*
     		  * This draws the Binary clock dynamically each time.
     		  */
             g.drawString(string,30,240);      
        }     	
     
     	/*
     	 * Creates a variable under the object Calendar and assigns the hours, minutes,
     	 * and seconds to the respective variables. They are then places within the function
     	 * that calculates the Binary code for the given time and appended to the string.
     	 */
     	public String timeNow()
     		{
     		Calendar now = Calendar.getInstance();
     		int hrs = now.get(Calendar.HOUR_OF_DAY);
     		int min = now.get(Calendar.MINUTE);
     		int sec = now.get(Calendar.SECOND);
 		
     		String hourBits = calc_bits(hrs);
            String minBits = calc_bits(min);
            String secBits = calc_bits(sec);
            String time = hourBits+":"+minBits+":"+secBits;
     		
            return time;
     		}
     	
     	/*
     	 * This is the function that is used in order to convert the hours,
     	 * minutes, and seconds into the binary values. Strings of size 8 are
     	 * created (since that the highest value is 60, it ranges from 1-32) for
     	 * the hours, minutes, and seconds. If the given hour, minute, or second 
     	 * is greater than or equal to 32 a 1 is appended, if not a 0 is appended.
     	 */
     	
     	 public String calc_bits(int value){
             int bit, filter = 32;
             StringBuffer bitString = new StringBuffer("00");
             for (bit = 3; bit <= 8; bit++) {
                     if (value >= filter) {
                             bitString.append('1');
                             value -= filter;
                     }
                     else {
                             bitString.append('0');
                     }
                     filter = filter - (filter / 2);
             }
             return bitString.toString();
     } 	
}
