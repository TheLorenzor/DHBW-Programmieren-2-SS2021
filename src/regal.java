import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class regal extends JPanel {
    JButton[] paletten;
    Lager lager;
    produkt copy;
    GUI gui;
    Bilanz bilanz;
    public int mode; //0= movement --> 1 gleich ein auslagern bzw. löschen
    regal (GUI gui) {
        this.gui = gui;
        this.bilanz = this.gui.bilanz;
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
                JButton b = (JButton) e.getSource();
                if (mode ==0) { //if it is in normal movement mode
                    if (copy!=null) { //if something needs to be moved
                        short[] from_point =this.convert_regal_to_Lager(get_object(copy));
                        produkt to_icon = (produkt) b.getIcon();
                        short[] to_point = this.convert_regal_to_Lager(get_object(to_icon));
                        if(to_icon.id==-1){ //wenn null ist und das zu kopierende hinten ist muss es beide hinten sein, dass beide funktionieren
                            to_point[2]=from_point[2];
                        }
                        if (to_icon.id!=-1 && !to_icon.get_Small() &&copy.get_Small()) { //if it is a small icon that needs to be moved behind a big icon that is valid
                            to_point[2]=1;
                        }
                        if (to_icon.id!=-1&&to_icon.get_Small()&& !copy.get_Small()){ //if one is small
                            to_point[2]=0;
                        }
                        if (to_point[0]==from_point[0]&&to_point[1]==from_point[1]&&to_point[2]==from_point[2]) { //wenn beides das selbe ist
                            if (to_point[2]==0) {
                                to_point[2]=1;
                            } else{
                                to_point[2]=0;
                            }
                            if(lager.move_palette(from_point,to_point)==0){
                                copy = null;
                                Runnable t = () -> {
                                    bilanz.add_Buchung(bilanztyp.reaarange,from_point,to_point);
                                    gui.bilanz_label.setText("<html><font color='white' size='15'>Bilanz: "+gui.bilanz.summe+"€</font></html>");
                                    gui.repaint();
                                };
                                t.run();
                                this.update_lager();

                            }
                        } else if(lager.move_palette(from_point,to_point)==0) {
                            copy=null;
                            Runnable t = () -> {
                                bilanz.add_Buchung(bilanztyp.reaarange,from_point,to_point);
                                gui.bilanz_label.setText("<html><font color='white' size='15'>Bilanz: "+gui.bilanz.summe+"€</font></html>");
                                gui.repaint();
                            };
                            t.run();
                            this.update_lager();
                        }
                        copy=null;
                    } else {

                        produkt to_copy = (produkt) b.getIcon();
                        if (to_copy.id!=-1){
                            this.copy = to_copy;
                        }
                        mode =0;
                    }
                } else if (mode==-1 ) { //zerstören
                    produkt ic = (produkt) b.getIcon();
                    System.out.println(get_object(ic));
                    if (ic.id!=-1&&(this.get_object(ic)==4||this.get_object(ic)==9)) {
                        short[] point = convert_regal_to_Lager(this.get_object(ic));
                        if (lager.auslagern(point)) {
                            Runnable t =new Runnable() {

                                @Override
                                public void run() {
                                    bilanz.add_Buchung(bilanztyp.destroy,point,point);
                                    gui.bilanz_label.setText("<html><font color='white' size='15'>Bilanz: "+gui.bilanz.summe+"€</font></html>");
                                    gui.repaint();
                                }
                            };
                            t.run();
                        }
                    }
                    mode=0;
                } else if (mode==1) { //einlagern
                    produkt ic = (produkt) b.getIcon(); //get icon where it has been clicked
                    if((ic.id==-1||ic.get_Small())) {
                        short[] point =this.convert_regal_to_Lager(this.get_object(ic));
                        point[2]=0;
                        if (this.lager.einlagern(point,copy)) {
                            int pos = this.gui.find_produkt(copy);
                            if (pos>-1) {

                                Runnable run = () -> {
                                    gui.auftrag_menu.repaint();
                                    bilanz.add_Buchung(bilanztyp.auftrag,gui.news[pos]);
                                    gui.bilanz_label.setText("<html><font color='white' size='15'>Bilanz: "+gui.bilanz.summe+"€</font></html>");
                                    gui.auftrage[pos].setIcon(null);
                                    gui.auftrage[pos].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                    gui.auftrage[pos].removeActionListener(this.gui.auftrage[pos].getActionListeners()[0]);
                                    gui.auftrage_label[pos].setText("<html><font color='white'>---</font></html>");
                                    gui.repaint();
                                };
                                run.run();
                                this.gui.news[pos] = null;

                            }else {
                                System.err.println("Nichts gefunden");
                            }
                            this.mode =0;
                            this.copy = null;
                            this.update_lager();
                        }
                    }
                } else if (mode==2){ //auslagern
                    produkt ic = (produkt) b.getIcon(); //get icon where it has been clicked
                    if (ic.getEigen()==copy.getEigen()&&ic.getType()==copy.getType()&&ic.getArt()==copy.getArt()) {
                        short [] point = this.convert_regal_to_Lager(this.get_object(ic));
                        if(lager.auslagern(point)) {
                            int pos =this.gui.find_produkt(copy);

                            Runnable run = () -> {
                                gui.auftrag_menu.repaint();
                                bilanz.add_Buchung(bilanztyp.auftrag,gui.news[pos]);
                                gui.bilanz_label.setText("<html><font color='white' size='15'>Bilanz: "+gui.bilanz.summe+"€</font></html>");
                                gui.repaint();
                                gui.news[pos]=null;
                                gui.auftrage[pos].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                gui.auftrage[pos].setIcon(null);
                                gui.auftrage[pos].removeActionListener(this.gui.auftrage[pos].getActionListeners()[0]);
                                gui.auftrage_label[pos].setText("<html><font color='white'>---</font></html>");
                            };
                            run.run();
                            this.mode =0;
                            this.copy = null;
                            this.update_lager();
                        }


                    }
                }
            });
            i++;
        }
        update_lager();
    }
    //TODO: anzeigen in GUI wie viel Euro gewinn
    public void update_lager() { //updates the regal to match the look
        if (this.mode<1) { //if it does not need to indicate where it is beeing pulled / pushed to this is used
            for (int i =0;i<10;i++) {
                short[] point = convert_regal_to_Lager(i);
                produkt t = (produkt) paletten[i].getIcon();
                if (lager.getLager()[point[0]][point[1]][0]==null && lager.getLager()[point[0]][point[1]][1]==null) {
                    //if both is empty than an empty produkt is set in as icon
                    paletten[i].setIcon(new produkt());
                } else if(lager.getLager()[point[0]][point[1]][0]!=null){
                    //else if is the point beein shown as normal size
                    paletten[i].setIcon(lager.getLager()[point[0]][point[1]][0]);
                } else if (lager.getLager()[point[0]][point[1]][1]!=null){
                    //else wise the back is used (to move it back it needs to pulled back where the small factor is also changed
                    paletten[i].setIcon(lager.getLager()[point[0]][point[1]][1]);
                }
                paletten[i].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        } else if (this.mode==1) { //if it is in einlagerungs modus
            for (int i=0;i<10;i++) { //für jedes einzelne Zeichen wird grün oder rot genutzt
                produkt val =(produkt) paletten[i].getIcon();
                short[] point = convert_regal_to_Lager(i);
                point[2]=0;
                if ((val.get_Small()||val.id==-1)&& lager.check_movement(copy,point,point)) {
                    paletten[i].setBorder(BorderFactory.createLineBorder(Color.green));
                } else {
                    paletten[i].setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        } else if (this.mode==2) {
            for (int i =0;i<10;i++) {
                produkt val = (produkt) paletten[i].getIcon();
                produkt to_compare = copy;
                if (val.getEigen()==to_compare.getEigen()&&val.getType()==to_compare.getType()&&val.getArt()==to_compare.getArt()) {
                    paletten[i].setBorder(BorderFactory.createLineBorder(Color.green));
                } else {
                    paletten[i].setBorder(BorderFactory.createLineBorder(Color.red));
                }
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


