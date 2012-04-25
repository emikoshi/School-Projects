

package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BallPanel extends Canvas {
	private static final long serialVersionUID = 1L;
	
	int score=0;
	int lives=3;
	boolean gameover=false;
	
    public BallPanel()
    {
        setSize(640, 480);
    }
    

    Random random = new Random();
    int newD()
    {
        return random.nextInt(6) + 2;
    }
    
    int dx1=1, dx3=1;
    int dy1=1, dy3=1;
    


	public void timestep()
    {
        ballx += dx1;
        bally += dy1;
          
        if (ballx > getWidth()) {
            ballx = getWidth();
            dx1 = newD() * -1;
        }
        if (ballx < 0) {
            ballx = 0;
            dx1 = newD() * 1 ;
        }
        if (bally > getHeight()) {
            bally = getHeight();
            dy1 = newD() * -1;
            lives--;
        }
        if (bally < 0) {
            bally = 0;
            dy1 = newD() * 1 ;
        }
        
	    if(bally>=panely+3 && bally>=panely-20 && ballx>=panelx && ballx<=panelx+120){

	    	score++;
	    	dy1 = newD() * -2;
	    }    
	    
	    if(lives==0){
	    	gameover=true;		        	
    }
    }

    public int ballx=100, bally = 100;
    public int panelx=200, panely=450;
    
    @Override
    public void paint(Graphics g)
    {
    	g.setColor(Color.BLACK);
        g.fillRect(panelx, 450, 120, 20);   
        g.setColor(Color.CYAN);
        g.fillOval(ballx-25, bally-25, 20, 20);
        
        g.setColor(Color.BLACK);
        g.drawString("Score: "+score, 10, 10);
        g.drawString("Lives: "+lives, 550,10);
        if(lives==0){
        	g.drawString("GAME OVER", 265, 250);  	
        	
    }
  }
}
