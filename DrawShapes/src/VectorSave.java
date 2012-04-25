import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is necessary because it is what saves your
 * picture to a text file once it has been edited. It takes the
 * main vector (shapeVector) which may or may have not been edited
 * and copies it to a new vector. With this the saveVector is run
 * run through and if the shape is there, the respective shape returns
 * its string and outputs to the file. 
 * 
 * @author Erick Mikoshi
 *
 */
public class VectorSave extends MouseAdapter implements ActionListener{
	
	Frame parent;
	DrawingCanvas tc;
	
	public VectorSave(Frame parent, DrawingCanvas tc){
		this.parent = parent;
		this.tc = tc;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0){
			
		//Creates a file dialog box, lets the user type up a file and save it.
		FileDialog f = new FileDialog(parent, "Save Your File");
		f.setMode(FileDialog.SAVE);
		f.setVisible(true);

		//Gets the filename and the directory it originates from
		String filename = f.getFile();
		String directory = f.getDirectory();

		
			/*
			 * With this try part of the statement a new PrintWriter out is made
			 * which is used in order for a file to be output as and saved as.
			 * Then with the for-loop each shape within shapeVector is run through
			 * if it isn't null, the string of that specific shape is written to out.
			 * The stream is then closed with out.close(). The catch is for the IOexception
			 * and prints out exactly where the program was at the moment of the exception.
			 */
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(directory+filename)));
		
				for(Shape s: tc.shapeVector){
					if(s!=null){
						out.println(s.writeString());
					}
				}
				out.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

}
