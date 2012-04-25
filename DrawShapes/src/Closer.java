import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is the Closer class, which extends the built in class
 * WindowAdapter. A window event is then added so the Frame can
 * be closed correctly.
 * @author Erick Mikoshi
 *
 */
public class Closer extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
