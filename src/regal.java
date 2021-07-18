import javax.swing.*;
import java.awt.*;

public class regal extends JPanel {
    regal () {
        setSize(700,800);
        setOpaque(false);
        setLocation(440,30);

    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon img = createImageIcon("Regal.png");
        img.setImage(img.getImage().getScaledInstance(700,800,Image.SCALE_DEFAULT));
        g.drawImage(img.getImage(),0,0,null);
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
