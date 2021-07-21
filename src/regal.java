import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class regal extends JPanel {
    produkt[] paletten;
    final produkt[] paletten_ref;
    public Lager lager;
    regal () {
        setSize(700,800);
        setOpaque(false);
        setLocation(440,30);
        this.setLayout(null);

        paletten = new  produkt[10];
        lager = new Lager();

        int i =0;
        for (produkt label:paletten){
            label = new produkt();
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
        paletten_ref = paletten;

        short[] t= {1,3,0};
        lager.einlagern(t,new produkt(produkttyp.Stein,produkttyp.Granit,produkttyp.leicht));
        short[] to = {1,3,1};
        lager.move_palette(t,to);
        lager.move_palette(to,t);
        this.update_regal();
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
    public void update_regal(){
        produkt[][][] to_check = lager.getLager();
        for (short i =0;i<5;i++) {
            for (short j = 0;j<2;j++) {
                short[] point = {j,i};
                int pal =convert_regal_to_Lager(point);
                Rectangle bounds = paletten_ref[pal].getBounds();
                this.remove(paletten[pal]);
                if (to_check[j][i][0]!=null){
                    paletten[pal] = to_check[j][i][0];
                    paletten[pal].setBounds(bounds);
                } else if(to_check[j][i][1]!=null) {
                    paletten[pal] = to_check[j][i][1];
                    paletten[pal].setBounds(bounds.x+30,bounds.y+20,bounds.width-50,bounds.height-50);
                    System.out.println(paletten[pal].getIcon().getIconHeight());
                    System.out.println(paletten[pal].getIcon().getIconWidth());
                } else {
                    paletten[pal] = new produkt();
                    paletten[pal].setBounds(bounds);

                }
                this.add(paletten[pal]);
            }
        }
        this.validate();
        this.repaint();
    }

}

