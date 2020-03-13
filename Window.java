import javax.swing.*;

/**
 * Window class - representation of a single window
 * @author Sergio Vasquez
 */
public class Window extends JFrame {

    /**
     * Constructor - Creates a single Window
     */
    public Window() {
        setBounds(100, 100, 500, 500);
        setTitle("TaskList");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new Panel());
        setVisible(true);
    }

    public static void main(String[] args) {
        Window w = new Window();
    }
}
