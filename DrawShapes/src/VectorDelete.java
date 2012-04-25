import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

/**
 * This class is necessary for the deletion of the shapes.
 * It extends mouseAdapter so another class does not have
 * to be made and so that the x and y of the clicker can be
 * passed into the isInside function. If it returns true
 * based off the two points it will remove the shape and repaint.
 * @author Erick Mikoshi
 *
 */

public class VectorDelete extends MouseAdapter implements ActionListener{
	
	DrawingCanvas tc;
	
	public VectorDelete(DrawingCanvas tc){
		this.tc = tc;
	}
	
	MouseListener u = new MouseListener(){
		//When the mouse is pressed the x and y of the clicker is found
		public void mousePressed(MouseEvent e){
			int x1 = e.getX();
			int y1= e.getY();
			
			Vector<Shape> copiedVector = new Vector<Shape>();
			
			//Copies a vector because you cannot go through a vector while deleting on it
			copiedVector.addAll(tc.shapeVector);
			
			//Goes through each shape in the vector, if the clicker returns
			//true for the isInside function. The shape is removed from the vector and canvas.
			for(Shape s: copiedVector){
				if(s.isInside(x1,y1)==true){
					tc.shapeVector.remove(s);
				}
				tc.repaint();
			}
				tc.removeMouseListener(u);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	};
	
		
	
	//When the delete button is clicked, whatever is in action performed will execute
	@Override
	public void actionPerformed(ActionEvent arg0){
			tc.addMouseListener(u);
	}
	
	
	
	
}
