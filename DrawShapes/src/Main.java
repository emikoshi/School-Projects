import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JColorChooser;


/**
 * The Main class is very important because
 * it is what executes the program all together. Here
 * is where the frame is created which houses all the 
 * graphics and shows what is executed.
 * @author Erick Mikoshi
 *
 */

public class Main {

	public static void main(String[] args) {
		//Creates a new frame f
		final Frame f = new Frame();
		//Creates a new frame g, for the toolbox
		final JFrame g = new JFrame();
		//Add a window listener with the Closer function so it closes properly closes
		f.addWindowListener(new Closer());

		//Creates a new drawing canvas tc
		final DrawingCanvas tc = new DrawingCanvas();
		
		
		//Adds the drawing canvas tc to the frame f so it is viewable
		f.add(tc);
		//Changes the position of g so it isn't stuck under frame f and sets a size
		g.setSize(200,300);
		g.setLocation(850,0);
		
		//Creates a new menu bar and adds it to the frame f
		MenuBar mb = new MenuBar();
        f.setMenuBar(mb);
        //The file button is added to the menu bar
        Menu m = new Menu("File");
        mb.add(m);  
        
        //'Open' is added as a menu item
        MenuItem m1 = new MenuItem("Open");
        m.add(m1);
        //An action listener is added to it so ButtonResponder can execute and pop up a dialog
        m1.addActionListener(new ButtonResponder(f,tc));
        
        //Create a save option under File and has VectorSave tied to it with an action listener
        MenuItem m2 = new MenuItem("Save");
        m.add(m2);
        m2.addActionListener(new VectorSave(f,tc));
        
        /*
         * This first adds a button, 'Export to PNG' to the menu, as well as adding an action listener
         * to it. A new BufferedImage is created with parameters to get the width of the canvas,
         * height of the canvas, and the type of RGB to use. A new graphics is made so it can pull
         * the image from BufferedImage. The canvas then prints the graphic to be used and graphic 
         * is thrown out. Then within the try statment, a new FileDialog is made which is much like
         * the one that was used for save. The import ImageIO is used to write the image as a PNG
         * to the specified directory and filename of the user. The catch statement is there so
         * if there is an IOException, the error is printed exactly where it happened.
         */
        MenuItem m3 = new MenuItem("Export to PNG");
        m.add(m3);
        m3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final BufferedImage image = new BufferedImage(tc.getWidth(), tc.getHeight(), BufferedImage.TYPE_INT_ARGB);
						Graphics gr = image.getGraphics();
						tc.printAll(gr);
						gr.dispose();
						try {
							FileDialog fd = new FileDialog(f, "Save Your Picture");
							fd.setMode(FileDialog.SAVE);
							fd.setVisible(true);
							String filename = fd.getFile();
							String directory = fd.getDirectory();
							ImageIO.write(image, "PNG", new File(directory+filename));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
			}	
        });
        
        //Adds toolbox to the menubar
        Menu mt = new Menu("Toolbox");
        mb.add(mt);
        
        //Creates an open option for the toolbox, has an actionlistener to show the toolbox.
        MenuItem mt1 = new MenuItem("Open");
        mt.add(mt1);
        mt1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
		        g.setVisible(true);
		        g.pack();
			}	
        });
             
        //Creates all the the various buttons for the toolbox
        Button del = new Button("Delete");
        Button cir = new Button("Add Circle");
        Button fillcir = new Button("Add FilledCircle");
        Button sqr = new Button("Add Square");
        Button fillsqr = new Button("Add FilledSquare");
        Button rec = new Button("Add Rectangle");
        Button fillrec = new Button("Add FilledRectangle");
        Button line = new Button("Add Line");
        Button color = new Button("Choose Color");
       
        //Adds the created buttons to the toolbox and adds a new VectorDraw to each button, linked to an ActionListener.
        BoxLayout boxLayout = new BoxLayout(g.getContentPane(), BoxLayout.Y_AXIS);
        g.setLayout(boxLayout);
        g.add(cir);
        cir.addActionListener(new VectorDraw(tc,cir));
        g.add(fillcir);
        fillcir.addActionListener(new VectorDraw(tc,fillcir));
        g.add(sqr);
        sqr.addActionListener(new VectorDraw(tc,sqr));
        g.add(fillsqr);
        fillsqr.addActionListener(new VectorDraw(tc,fillsqr));
        g.add(rec);
        rec.addActionListener(new VectorDraw(tc,rec));
        g.add(fillrec);
        fillrec.addActionListener(new VectorDraw(tc,fillrec));
        g.add(line);
        line.addActionListener(new VectorDraw(tc,line));
        g.add(del);
        del.addActionListener(new VectorDelete(tc));
        
        /*
         * This adds a new button to the toolbox with the appropriate action listener.
         * A new JColorChooser dialog is created with the canvas its responsible for, the
         * title of it, and the default color(which is also the color when reset is pressed).
         * When this dialog pops up, whatever color the user chooser and then presses ok is made 
         * to be the Color c. The RGB is then retrieved from the color with getRed, getGreen, and
         * getBlue. The color is then added to shapeVector and can be used to color the shapes.
         */
        g.add(color);
        color.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		Color c = JColorChooser.showDialog(tc, "Chose your color!", Color.BLACK);
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				tc.shapeVector.add(new ColorShapes(r,g,b));
				tc.repaint();
        	}
        }); 

        g.pack();
        g.setVisible(false);
        
        //To show the frame f
		f.pack();
		f.setVisible(true);
	}

}
