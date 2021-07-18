import javax.swing.*;
import java.awt.*;

public class regal extends JPanel {
    JLabel paletten[];

    regal () {
        setSize(700,800);
        setOpaque(false);
        setLocation(440,30);
        this.setLayout(null);
        paletten = new  JLabel[10];
        int i =0;
        for (JLabel label:paletten){
            ImageIcon icon = createImageIcon("leer.png");
            icon.setImage(icon.getImage().getScaledInstance(193,140,Image.SCALE_DEFAULT));
            label = new JLabel(icon);
            paletten[i]=label;
            int x;
            if (i<5) {
                x =100;
            } else {
                x=420;
            }
            int y = -30+(i%5)*170;
            paletten[i].setBounds(x,y,183,140); //calculates position so it calculates everythin
            this.add(paletten[i]);
            i++;
        }

        ImageIcon icon2 = createImageIcon("Stein/Granit_leicht.png");
        icon2.setImage(icon2.getImage().getScaledInstance(193,140,Image.SCALE_DEFAULT));
        JLabel test2 =new JLabel(icon2);
        test2.setBounds(paletten[6].getBounds());
        this.remove(paletten[6]);
        this.validate();
        this.repaint();
        paletten[6] = test2;
        this.add(test2);

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
