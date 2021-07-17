import frontend.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class main {
    public static void main(String []args){
        JFrame t = new JFrame();
        JPanel auftrag = new JPanel();
        ImageIcon back = new ImageIcon(Objects.requireNonNull(main.class.getClassLoader().getResource("Background2.png")));
        JLabel backgdrop = new JLabel(back);
        t.setDefaultCloseOperation(3);
        t.setPreferredSize(new Dimension(100,100));
        t.pack();
        t.setContentPane(backgdrop);
        t.setVisible(true);
    }
}

