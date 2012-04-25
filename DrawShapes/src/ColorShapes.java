import java.awt.Color;
import java.awt.Graphics;

/**
 * This class ColorShapes is necessary to color whatever shapes
 * follow the ColorShapes function, until another ColorShapes is called. 
 * The ColorShapes function is created with 3 parameters (integer of x[red], 
 * integer of y[green], and integer of z[blue]).
 * @author Erick Mikoshi
 *
 */
public class ColorShapes extends Shape{
	int x,y,z;
	
	/*
	 * Creates the function ColorShapes and uses 'this' on
	 * the variables so the variables for the class as a whole are used
	 * rather than the ones in the parameter.
	 */
	public ColorShapes(int x, int y, int z){
		this.x=x;
		this.y=y;
		this.z=z;	
	}
	
	/*
	 * This is what actually sets the color with the
	 * given rgb values. A new instance of Color n has
	 * to be created, since setColor cannot take in 
	 * three integers.
	 */
	public void draw(Graphics g){
		Color n = new Color(x,y,z);
		g.setColor(n);
	}
	/*
	 * This function is needed because for each time color occurs
	 * within the vector, a string gets returned with the color
	 * and respective rgb. To later be outed in a text file for
	 * saving.
	 */
	public String writeString(){
		return "color"+" "+x+" "+y+" "+z;
	}

}
