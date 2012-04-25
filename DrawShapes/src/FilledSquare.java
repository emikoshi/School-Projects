import java.awt.Graphics;

/**
 * This class Filled Square is necessary to draw a filled
 * square. The Filled Square function is created with 3 parameters
 * (integer of x, integer of y, integer of z[sides]).
 * @author Erick Mikoshi
 *
 */
public class FilledSquare extends Shape{
	int x,y,z;
	
	/*
	 * Creates the function Filled Square and uses 'this' on
	 * the variables so the variables for the class as a whole are used
	 * rather than the ones in the parameter.
	 */
	public FilledSquare(int x, int y, int z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	/*
	 * This is needed to draw the filled square to the canvas,
	 * some mathematics are applied to center the shape on 
	 * the canvas.
	 */
	public void draw(Graphics g){
		g.fillRect(x-(z/2),y-(z/2),z,z);
	}
	/*
	 * This boolean is needed to check whether or not the clicker 
	 * is within each filled square. If the clicker is, return true if 
	 * not return false. This function is used later on
	 * because if it returns true, its used in VectorDelete to
	 * remove it from the canvas based off user clicks.
	 */
	public boolean isInside(int x1, int y1){
		int t = y -(z/2);
		int b = y +(z/2);
		int r = x +(z/2);
		int l = x -(z/2);
		
		if(x1<r && x1>l && y1<b && y1>t){
			System.out.println("FilledSquare: "+x1+" "+y1+" "+z+" is being deleted.");
			return true;
		}	
		else
			return false;	
	}
	/*
	 * This function is needed because for each filled square with
	 * in the vector, a string gets returned with the filled square
	 * and its dimensions. To later be outed in a text file for
	 * saving.
	 */
	public String writeString(){
		return "filledsquare"+" "+x+" "+y+" "+z+" "+z;
	}

}