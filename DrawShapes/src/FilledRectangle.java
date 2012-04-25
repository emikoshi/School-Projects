import java.awt.Graphics;

/**
 * This class FilledRectangle is necessary to draw a filled
 * rectangle. The FilledRectangle function is created with 4 parameters
 * (integer of x, integer of y, integer of z[width], integer of r[height]).
 * @author Erick Mikoshi
 *
 */
public class FilledRectangle extends Shape{
	int x,y,z,r;
	
	/*
	 * Creates the function FilledRectangle and uses 'this' on
	 * the variables so the variables for the class as a whole are used
	 * rather than the ones in the parameter.
	 */
	public FilledRectangle(int x, int y, int z, int r){
		this.x=x;
		this.y=y;
		this.z=z;
		this.r=r;
	}
	
	/*
	 * This is needed to draw the filled rectangle to the canvas,
	 * some mathematics are applied to center the shape on 
	 * the canvas.
	 */
	public void draw(Graphics g){
		g.fillRect(x-(z/2),y-(r/2),z,r);
	}
	/*
	 * This boolean is needed to check whether or not the clicker 
	 * is within each filled rectangle. If the clicker is, return true if 
	 * not return false. This function is used later on
	 * because if it returns true, its used in VectorDelete to
	 * remove it from the canvas based off user clicks.
	 */
	public boolean isInside(int x1, int y1){
		int t = y -(r/2);
		int b = y +(r/2);
		int r = x +(z/2);
		int l = x -(z/2);
		
		if(x1<r && x1>l && y1<b && y1>t){
			System.out.println("FilledRectangle: "+x1+" "+y1+" "+z+" "+r+" is being deleted.");
			return true;
		}	
		else
			return false;
	}
	/*
	 * This function is needed because for each filled rectangle with
	 * in the vector, a string gets returned with the filled rectangle
	 * and its dimensions. To later be outed in a text file for
	 * saving.
	 */
	public String writeString(){
		return "filledrectangle"+" "+x+" "+y+" "+z+" "+r;
	}

}