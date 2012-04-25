import java.awt.Graphics;

/**
 * This function Shape is required so all the various
 * shape classes can all extend to it(Shape). So then
 * the shape classes can be objects of the vector
 * shapeVector. 
 * @author Erick Mikoshi
 *
 */
public class Shape {
	public void draw(Graphics g){}
	
	//Implemented so that each shape class is able to use isInside
	public boolean isInside(int x1, int y1){
		return false;
	}
	
	//Implemented so that each shape class can return a String with writeString
	public String writeString(){
		return "";
	}

}
