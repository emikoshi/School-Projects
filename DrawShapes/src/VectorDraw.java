import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;


/**
 * The purpose of this class is to be able to draw shapes
 * to the canvas. The user will be allowed to open a toolbox and 
 * click on which shape they want to draw. Each shape will be drawn from the center
 * to the side for a circle, the center to the bottom right corner for rectangles/squares
 * and end to end for a line. There is also a delete button for the user if they want to
 * delete a shape. VectorDraw extends MouseAdapter in order for the clicks to be retrieved,
 * and it also implements ActionListener so that it may be linked to the buttons. After a shape
 * is drawn, the user will have to click the button again to draw/delete another shape.
 * 
 * 
 * @author Erick Mikoshi
 *
 */



public class VectorDraw extends MouseAdapter implements ActionListener{
	DrawingCanvas tc;
	JLabel l;
	Button b;
	
	int x1,y1,x2,y2;
	
	//Creates a new MouseListener so it can be used in remove MouseListener later on.
	MouseListener u = new MouseListener(){	

			//Gets the coordinates of the click when the mouse is pressed.
			public void mousePressed(MouseEvent e){
					x1 = e.getX();
					y1 = e.getY();
			}
			
			
			/*
			 * Gets the x and y of the mouse where ever it is released. Then a series
			 * of if-statements are run through. Depending on the label of the button,
			 * it will draw the shape of the respective button by adding the shape to 
			 * the vector. This is much like the method that was used in ButtonResponder.
			 * It is then repainted and the MouseListener is removed so when another button
			 * is clicked the latter shape isn't also drawn.
			 * 
			 */

			@Override
			public void mouseReleased(MouseEvent e) {
					x2 = e.getX();
					y2 = e.getY();
		
					if(b.getLabel()==("Add Circle")){
						double dist = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
						tc.shapeVector.add(new Circle(x1,y1,(int)dist));
						tc.repaint();
						tc.removeMouseListener(u);
					}
					if(b.getLabel()==("Add FilledCircle")){
						double dist = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
						tc.shapeVector.add(new FilledCircle(x1,y1,(int) dist));
						tc.repaint();
						tc.removeMouseListener(u);
					}
					if(b.getLabel()==("Add Rectangle")){
						double distx = Math.sqrt((x2-x1)*(x2-x1));
						double disty = Math.sqrt((y2-y1)*(y2-y1));
						tc.shapeVector.add(new Rectangle(x1,y1,(int)distx*2,(int)disty*2));
						tc.repaint();
						tc.removeMouseListener(u);
					}
					if(b.getLabel()==("Add FilledRectangle")){
						double distx = Math.sqrt((x2-x1)*(x2-x1));
						double disty = Math.sqrt((y2-y1)*(y2-y1));
						tc.shapeVector.add(new FilledRectangle(x1,y1,(int)distx*2 ,(int)disty*2));
						tc.repaint();
						tc.removeMouseListener(u);
					}
					if(b.getLabel()==("Add Square")){
						double distx = Math.sqrt((x2-x1)*(x2-x1));
						double disty = Math.sqrt((y2-y1)*(y2-y1));
						int totdist = (int) (distx+disty);
						tc.shapeVector.add(new Square(x1,y1,totdist));
						tc.repaint();
						tc.removeMouseListener(u);
					}
					if(b.getLabel()==("Add FilledSquare")){
						double distx = Math.sqrt((x2-x1)*(x2-x1));
						double disty = Math.sqrt((y2-y1)*(y2-y1));
						int totdist = (int) (distx+disty);
						tc.shapeVector.add(new FilledSquare(x1,y1,totdist));
						tc.repaint();
						tc.removeMouseListener(u);
					}

					if(b.getLabel()==("Add Line")){
						tc.shapeVector.add(new Line(x1,y1,x2,y2));
						tc.repaint();
						tc.removeMouseListener(u);
					}
					
			}
					

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
		
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	};
	
	public VectorDraw(DrawingCanvas tc,Button b){
		this.tc=tc;
		this.b=b;
	}
	
	//Adds the mouse listener to the canvas tc
	public void actionPerformed(ActionEvent arg0) {
		tc.addMouseListener(u); 

	}
}

			
		

