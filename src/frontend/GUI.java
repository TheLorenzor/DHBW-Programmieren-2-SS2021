package frontend;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GUI extends JFrame {
    JLabel[] auftrage;
    public GUI() {
        super("Lagersimulation");

        auftrage = new JLabel[4];
        JPanel auftrag = new JPanel();
        ImageIcon back = new ImageIcon(Objects.requireNonNull(GUI.class.getClassLoader().getResource("Background2.png"))
        );
        JLabel backgdrop = new JLabel(back);

        this.pack();
        this.setDefaultCloseOperation(3);
        this.setContentPane(backgdrop);
        this.setVisible(true);
    }
}
