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
            label = new JButton();
            paletten[i]=label;
            paletten[i].setIcon(new produkt());
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
                if (mode ==0) { //if it is in normal movement mode
                    JButton b = (JButton) e.getSource();
                    if (copy!=null) { //if something needs to be moved
                        short[] from_point =this.convert_regal_to_Lager(get_object(copy));
                        produkt to_icon = (produkt) b.getIcon();
                        short[] to_point = this.convert_regal_to_Lager(get_object(to_icon));
                        if(to_icon.id==-1){
                            to_point[2]=from_point[2];
                        }
                        if (to_point[0]==from_point[0]&&to_point[1]==from_point[1]&&to_point[2]==from_point[2]) {
                            if (to_point[2]==0) {
                                to_point[2]=1;
                            } else{
                                to_point[2]=0;
                            }
                            if(lager.move_palette(from_point,to_point)==0){
                                 copy = null;

                            }
                        } else if(lager.move_palette(from_point,to_point)==0) {
                            copy=null;
                            update_lager();
                        }
                        copy=null;
                    } else {

                        produkt to_copy = (produkt) b.getIcon();
                        if (to_copy.id!=-1){
                            this.copy = to_copy;
                        }
                        mode =0;
                    }
                }
            });
            i++;
        }
        short[] t= {1,3,0};
        lager.einlagern(t,new produkt(produkttyp.Stein,produkttyp.Granit,produkttyp.leicht));
        short[] to = {1,4,0};
        lager.einlagern(to,new produkt(produkttyp.Holz,produkttyp.Kiefer,produkttyp.Bretter));

        update_lager();
    }
    public void update_lager() {
        for (int i =0;i<10;i++) {
            short[] point = convert_regal_to_Lager(i);
            produkt t = (produkt) paletten[i].getIcon();
            if (lager.getLager()[point[0]][point[1]][0]==null && lager.getLager()[point[0]][point[1]][1]==null) {
                paletten[i].setIcon(new produkt());
            } else if(lager.getLager()[point[0]][point[1]][0]!=null){
                paletten[i].setIcon(lager.getLager()[point[0]][point[1]][0]);
            } else if (lager.getLager()[point[0]][point[1]][1]!=null){
                paletten[i].setIcon(lager.getLager()[point[0]][point[1]][1]);
            }
        }
        this.validate();
        this.repaint();
    }



    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon img = new ImageIcon(getClass().getResource("Regal.png"));
        img.setImage(img.getImage().getScaledInstance(700,800,Image.SCALE_DEFAULT));
        g.drawImage(img.getImage(),0,0,null);
    }
    private int get_object(produkt needle){
        for (int i=0;i<10;i++){
            if (paletten[i].getIcon().equals(needle)){
                return i;
            }
        }
        return -1;
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
    public short[] convert_regal_to_Lager(int pos) {
        produkt prod = (produkt) paletten[pos].getIcon();
        short i =0;
        if (prod.get_Small()) {
            i=1;
        }
         switch (pos) {
             case 0: return new short[]{0,4,i};
             case 1: return new short[]{0,3,i};
             case 2: return new short[]{0,2,i};
             case 3: return new short[]{0,1,i};
             case 4: return new short[]{0,0,i};
             case 5: return new short[]{1,4,i};
             case 6: return new short[]{1,3,i};
             case 7: return new short[]{1,2,i};
             case 8: return new short[]{1,1,i};
             default: return new short[]{1,0,i};
         }
    }
}


