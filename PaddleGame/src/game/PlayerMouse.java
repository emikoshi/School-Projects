package game;


import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;



public class PlayerMouse extends MouseMotionAdapter {
    BallPanel l;
    public PlayerMouse(BallPanel l)
    {
        this.l = l;
    }
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
        l.panelx=e.getX();
    }
}
