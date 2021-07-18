import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class GUI extends JFrame {
    JLabel[] auftrage;
    JPanel main;
    ImageIcon background;
    Dimension full_frame = new Dimension(1000,700);
    public GUI(URL url) {
        super("Lagersimulation");

        background = createImageIcon("Background.jpg");
        background.setImage(background.getImage().getScaledInstance(1000,700,Image.SCALE_DEFAULT));
        JLabel backgdrop = new JLabel(background);
        this.setContentPane(backgdrop);

        this.pack();
        this.setSize(full_frame);
        this.setResizable(false);
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
