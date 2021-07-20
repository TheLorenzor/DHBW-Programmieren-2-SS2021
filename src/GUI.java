import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

public class GUI extends JFrame {
    Bilanz bilanz;
    JPanel auftrag_menu;
    JPanel menu;
    regal main;

    JLabel[] auftrage;
    JLabel bilanz_label;
    ImageIcon background;
    Dimension full_frame = new Dimension(1400,900);

    public GUI() {
        super("Lagersimulation");
        main = new regal();
        bilanz = new Bilanz();
        background = createImageIcon("Background.png");
        background.setImage(background.getImage().getScaledInstance(1400,900,Image.SCALE_DEFAULT));
        JLabel backgdrop = new JLabel(background);
        this.setContentPane(backgdrop);

        auftrag_menu = new JPanel();
        auftrag_menu.setBackground(Color.RED);
        auftrag_menu.setLayout(null);
        auftrag_menu.setSize(400,500);
        auftrag_menu.setLocation(13,330);
        auftrag_menu.setOpaque(false);

        JButton new_auftrag = new JButton();
        ImageIcon auftrag_icon = new ImageIcon(getClass().getResource("add_auftrag.png"));
        auftrag_icon.setImage(auftrag_icon.getImage().getScaledInstance(90,90,1));
        new_auftrag.setIcon(auftrag_icon);
        new_auftrag.setBounds(66,5,90,90);
        new_auftrag.setOpaque(false);
        new_auftrag.setContentAreaFilled(false);
        auftrag_menu.add(new_auftrag);

        JButton del_auftrag = new JButton();
        ImageIcon del_icon = new ImageIcon(getClass().getResource("del_auftrag.png"));
        del_icon.setImage(del_icon.getImage().getScaledInstance(90,90,1));
        del_auftrag.setIcon(del_icon);
        del_auftrag.setBounds(233,5,90,90);
        del_auftrag.setOpaque(false);
        del_auftrag.setContentAreaFilled(false);
        auftrag_menu.add(del_auftrag);

        this.auftrage = new JLabel[4];
        for (int i = 0;i<4;++i){
            this.auftrage[i] = new JLabel("",SwingConstants.CENTER);
            int x = (i%2)*200+6;
            int y;
            if (i<2) {
                y=100;
            } else {
                y=300;
            }
            this.auftrage[i].setBounds(x,y,190,190);
            this.auftrage[i].setBackground(Color.decode("#D2D4D4"));
            this.auftrage[i].setOpaque(true);
            auftrag_menu.add(this.auftrage[i]);
        }

        menu = new JPanel();
        menu.setLayout(null);
        menu.setSize(150,800);
        menu.setLocation(1165,28);
        menu.setOpaque(false);

        JButton see_bilanz = new JButton();
        ImageIcon see_bilanz_icon = new ImageIcon(getClass().getResource("see_bilanz.png"));
        see_bilanz_icon.setImage(see_bilanz_icon.getImage().getScaledInstance(130,130,1));
        see_bilanz.setIcon(see_bilanz_icon);
        see_bilanz.setBounds(10,10,130,130);
        see_bilanz.setOpaque(false);
        see_bilanz.setContentAreaFilled(false);
        see_bilanz.addActionListener(e -> {
            if (e.getSource().getClass()==see_bilanz.getClass()) {
                bilanz.setVisible(true);
            }
        });
        menu.add(see_bilanz);

        JLabel desc1 = new JLabel("<html><font color='white' size='4'>Bilanz einsehen</font></html>",SwingConstants.CENTER);
        desc1.setBounds(5,145,140,30);
        desc1.setOpaque(false);
        menu.add(desc1);


        bilanz_label=new JLabel("<html><font color='white' size='15'>Bilanz: 0€</font></html>",SwingConstants.LEFT);
        bilanz_label.setBounds(13,100,300,50);
        bilanz_label.setBackground(Color.decode("#323a3a"));
        bilanz_label.setOpaque(true);

        this.add(menu);
        this.add(auftrag_menu);
        this.add(main);
        this.add(bilanz_label);

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
