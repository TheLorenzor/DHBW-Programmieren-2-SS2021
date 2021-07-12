
import javax.swing.*;
import java.awt.*;

public class frontend extends JFrame {
    public frontend() throws HeadlessException{
        super();
        this.setTitle("Logistikspiel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        GridLayout store = new GridLayout(2,5,5,5);
        this.setVisible(true);




    }
}
