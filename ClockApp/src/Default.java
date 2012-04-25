import java.awt.*;

/**
 * This is the Default class, it wasn't entirely necessary. But
 * I thought that it would be nice to greet the user with a message
 * and not be defaulted with a certain clock. It's a very simple class.
 * @author Yosh
 *
 */

public class Default extends Canvas{
	private Font font = new Font("Helvetica", Font.BOLD, 20);
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Sets what the size of the canvas will be when Default is open.
	 */
	public Default(){
		setSize(480,480);
	}
	/*
	 * The paint function is called and displays the greeting message,
	 * along with the appropriate colors.
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("Please choose a clock!", 130,230);
	}

}
