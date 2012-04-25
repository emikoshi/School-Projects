package game;
import java.awt.event.*;


public class Closer extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
