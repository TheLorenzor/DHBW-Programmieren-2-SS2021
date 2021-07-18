import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class GUI extends JFrame {
    JPanel auftrag_menu;
    JPanel menu;
    regal main;
    ImageIcon background;
    Dimension full_frame = new Dimension(1400,900);
    public GUI() {
        super("Lagersimulation");
        main = new regal();
        background = createImageIcon("Background.png");
        background.setImage(background.getImage().getScaledInstance(1400,900,Image.SCALE_DEFAULT));
        JLabel backgdrop = new JLabel(background);
        this.setContentPane(backgdrop);

        auftrag_menu = new JPanel();
        auftrag_menu.setBackground(Color.RED);
        auftrag_menu.setSize(400,500);
        auftrag_menu.setLocation(13,330);

        menu = new JPanel();
        menu.setSize(150,800);
        menu.setLocation(1165,28);
        this.add(menu);
        this.add(auftrag_menu);
        this.add(main);
        this.pack();
        this.setSize(full_frame);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL !=null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldnt find file");
            return null;
        }
    }
}
