import java.awt.*;

import java.util.Vector;
/**
 * This class DrawingCanvas is important because it is
 * what actually draws the shapes to the canvas.
 * @author Erick Mikoshi
 *
 */
public class DrawingCanvas extends Canvas {
	
	private static final long serialVersionUID = 1L;
	/*
	 * Creates the function DrawingCanvas and
	 * sets the size of the canvas.
	 */
	
	public DrawingCanvas(){
		setSize(800,600);
	}

	//Creates a vector called ShapeVector
	Vector<Shape> shapeVector = new Vector<Shape>();

	/*
	 * The for statement within the paint function
	 * is necessary because it goes through shapeVector
	 * and retrieves each Shape s and draws it to 
	 * DrawingCanvas.
	 */
	@Override
	public void paint(Graphics g){
		
		for(Shape s: shapeVector){
			s.draw(g);
		}
	}
}



