import javax.swing.*;
import java.awt.*;
public class regal extends JPanel {
    JButton[] paletten;
    Lager lager;
    produkt copy;
    public int mode; //0= movement --> 1 gleich ein auslagern bzw. löschen
    regal () {
        setSize(700,800);
        setOpaque(false);
        setLocation(440,30);
        this.setLayout(null);
        mode = 0;
        paletten = new  JButton[10];
        lager = new Lager();
        copy =null;
        int i =0;
        for (JButton label:paletten){
            label = new JButton("leer");
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
            paletten[i].setOpaque(false);
            paletten[i].setContentAreaFilled(false);
            paletten[i].addActionListener(e -> {
                if (mode ==0) {
                    JButton b = (JButton) e.getSource();
                    if (copy!=null) {
                        short[] point = lager.get_point(copy);

                    } else {
                        String n = b.getText();
                        if (!n.equals("leer")) {
                            copy =
                        }
                    }
                }
            });
            i++;
        }
        short[] t= {1,3,0};
        lager.einlagern(t,new produkt(produkttyp.Stein,produkttyp.Granit,produkttyp.leicht));
        short[] to = {1,3,1};
        lager.move_palette(t,to);
        lager.move_palette(to,t);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon img = new ImageIcon(getClass().getResource("Regal.png"));
        img.setImage(img.getImage().getScaledInstance(700,800,Image.SCALE_DEFAULT));
        g.drawImage(img.getImage(),0,0,null);
    }

    public int convert_regal_to_Lager(short[] point) {
        if (point[0]==0) {
            return switch (point[1]) {
                case 0 -> 4;
                case 1 -> 3;
                case 2 -> 2;
                case 3 -> 1;
                default -> 0;
            };
        } else {
            return switch (point[1]) {
                case 0 -> 9;
                case 1 -> 8;
                case 2 -> 7;
                case 3 -> 6;
                default -> 5;
            };
        }
    }
    public void convert_regal_to_Lager(int pos) {

    }
}

