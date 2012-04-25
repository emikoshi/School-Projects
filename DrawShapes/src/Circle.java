import java.awt.Graphics;

/**
 * This class Circle is necessary to draw the shape of
 * a circle. The Circle function is created with 3 parameters
 * (integer of x, integer of y, and integer of the radius).
 * @author Erick Mikoshi
 *
 */
public class Circle extends Shape{
	int x,y,r;
	
	/*
	 * Creates the function Circle and uses 'this' on
	 * the variables so the variables for the class as a whole are used
	 * rather than the ones in the parameter.
	 */
	public Circle(int x, int y, int r){	
		this.x=x;
		this.y=y;
		this.r=r;
	}
	
	/*
	 * This is needed to draw the circle to the canvas,
	 * some geometry is used to center the shape on 
	 * the canvas as well as getting the radius with
	 * multiplying the width and height.
	 */
	public void draw(Graphics g){
		g.drawOval(x-r,y-r,r*2,r*2);
		
	}
	/*
	 * This boolean is needed to check whether or not the clicker 
	 * is within each circle. If the clicker is, return true if 
	 * not return false. This function is used later on
	 * because if it returns true, its used in VectorDelete to
	 * remove it from the canvas based off user clicks.
	 */
	public boolean isInside(int x1, int y1){
		double dist = Math.sqrt((x1-x)*(x1-x)+(y1-y)*(y1-y));
		if(dist<r){
			System.out.println("Circle: "+x1+" "+y1+" "+r+" is being deleted.");
			return true;
		}
		else
			return false;
	}
	/*
	 * This function is needed because for each circle with
	 * in the vector, a string gets returned with the circle
	 * and its dimensions. To later be outed in a text file for
	 * saving.
	 */
	public String writeString(){
		return "circle"+" "+x+" "+y+" "+r;
	}

}
