import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * The most important function of this java class is to pop up a dialog box 
 * that prompts the user to choose a text file from their own file system. After
 * doing so, the text file is read line by line (while being split up) and the
 * data is put through a series of if-statements and drawn to the screen.
 * 
 * @author Erick Mikoshi
 *
 */
public class ButtonResponder implements ActionListener {
	
	Frame parent;
	DrawingCanvas tc;
	
	/*
	 * This creates parameters for the function ButtonResponder of
	 * Frame parent and DrawingCanvas tc. It sets the parent and
	 * tc equal to what they are set as within this java class, by using
	 * 'this'. Then is added to the actionListener, so when 'open' is 
	 * clicked everything in ButtonResponder can follow through.
	 */
	public ButtonResponder(Frame parent, DrawingCanvas tc){
		this.parent = parent;
		this.tc = tc;
	}
	
	/*
	 * This function executes the following code when 'open'
	 * is clicked through the file menu. It is required for the
	 * popping up of the dialog, choosing a file, reading each 
	 * line within the text file, splitting each line by spaces,
	 * and adding the shape with respective dimensions to the vector.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Creates a file dialog box, lets the user select a file and load it.
		FileDialog f = new FileDialog(parent, "Select a file");
		f.setMode(FileDialog.LOAD);
		f.setVisible(true);
		
		String filename = f.getFile();
		String directory = f.getDirectory();
		System.out.println("The file is: " + directory+filename);
		
		/*
		 * Within the try part of the statement a new BufferedReader br is created
		 * which is needed to read from the line by using readLine(). The string 
		 * array p is necessary for split to be used and for each line to be broken
		 * up by spaces. The while statement is necessary so not just the first line
		 * is read but so that every line is read until the end of the file is reached.
		 * The slew of conditional if statements are necessary in order to see which shape
		 * it is and to add the shape to the vector shapeVector so DrawingCanvas can read 
		 * from it and draw it. The catch statements at the near end are vital to tell the
		 * user if the file was bad (ie not found) or it was of the wrong type (ie not
		 * being able to be found). And at the very end, tc is repainted so a shape is drawn
		 * to the canvas each time a line is read.
		 */
		try {
			BufferedReader br = new BufferedReader(new FileReader(directory+filename));
			String line = br.readLine();
			String[] p = line.split("  *");
			
			
			while(line != null){
				//System.out.println("The line is: "+line);
			
				if(p[0].equals("circle")){
					//System.out.println("Adding a Circle");
					tc.shapeVector.add(new Circle(Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3])));
				}
				if(p[0].equals("filledcircle")){
					//System.out.println("Adding a FilledCircle");
					tc.shapeVector.add(new FilledCircle(Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3])));
				}
				if (p[0].equals("square")){
					//System.out.println("Adding a Square");
					tc.shapeVector.add(new Square(Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3])));
				}
				if (p[0].equals("filledsquare")){
					//System.out.println("Adding a FilledSquare");
					tc.shapeVector.add(new FilledSquare(Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3])));
				}	
				if (p[0].equals("line")){
					//System.out.println("Adding a Line");
					tc.shapeVector.add(new Line(Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3]),Integer.parseInt(p[4])));
				}
				if (p[0].equals("rectangle")){
					//System.out.println("Adding a Rectangle");
					tc.shapeVector.add(new Rectangle(Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3]),Integer.parseInt(p[4])));
				}
				if (p[0].equals("filledrectangle")){
					//System.out.println("Adding a FilledRectangle");
					tc.shapeVector.add(new FilledRectangle(Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3]),Integer.parseInt(p[4])));
				}
				if (p[0].equals("color")){
					//System.out.println("Setting a Color");
					tc.shapeVector.add(new ColorShapes(Integer.parseInt(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3])));
				}
				
				line = br.readLine();
				
				if(line!=null){
					p=line.split("  *");
				}
			}								
		} catch (FileNotFoundException e) {
			System.out.println("BAD FILE");
		} catch (IOException ioe){
			System.out.println("COULD NOT READ FILE");
		}
		tc.repaint();
	}

}
