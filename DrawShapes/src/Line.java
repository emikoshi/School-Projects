import java.awt.Graphics;

/**
 * This class Line is necessary to draw a line.
 * The Line function is created with 4 parameters
 * (integer of x, integer of y, integer of z[x2], integer of r[y2]).
 * @author Erick Mikoshi
 *
 */
public class Line extends Shape{
	int x1,y1,x2,y2;
	
	/*
	 * Creates the function Line and uses 'this' on
	 * the variables so the variables for the class as a whole are used
	 * rather than the ones in the parameter.
	 */
	public Line(int x, int y, int x2, int y2){
		this.x1=x;
		this.y1=y;
		this.x2=x2;
		this.y2=y2;
	}
	
	/*
	 * This is needed to draw the line to the canvas.
	 */
	public void draw(Graphics g){
		g.drawLine(x1, y1, x2, y2);
	}
	/*
	 * This boolean is needed to check whether or not the clicker 
	 * is within each line. If the clicker is, return true if 
	 * not return false. This function is used later on
	 * because if it returns true, its used in VectorDelete to
	 * remove it from the canvas based off user clicks.
	 */
	public boolean isInside(int x3, int y3){
		double m = ((y2-y1)/((double)(x2-x1)));
		double b = ((-m)*x1)+y1;
		double x = (x3+(m)*y3-(m)*b)/(1+(m*m));
		double y = (m*x)+b;
		double dist = Math.sqrt((y3-y)*(y3-y)+(x3-x)*(x3-x));
		if(dist<2){
			System.out.println("Line: "+x1+" "+y1+" "+x2+" "+y2+" is being deleted.");
			return true;
		}
		else
			return false;	
	}
	/*
	 * This function is needed because for each line with
	 * in the vector, a string gets returned with the line
	 * and its dimensions. To later be outed in a text file for
	 * saving.
	 */
	public String writeString(){
		return "line"+" "+x1+" "+y1+" "+x2+" "+y2;
	}


}