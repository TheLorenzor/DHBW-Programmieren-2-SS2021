import javax.swing.*;
import java.awt.*;

public class storage extends JPanel {
    JButton[] elements;
    public storage () {
        super();
        GridLayout grid = new GridLayout(5,2);
        this.setLayout(grid);
        elements = new JButton[10];
        Integer i = 0;
        for (JButton button:elements) {
            i++;
            button = new JButton(i.toString());
            this.add(button);
        }


    }
}
