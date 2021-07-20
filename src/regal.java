import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class regal extends JPanel {
    produkt paletten[];
    private Lager lager;
    regal () {
        super();
        lager = new Lager();
        setSize(700,800);
        setOpaque(false);
        setLocation(440,30);
        this.setLayout(null);

        paletten = new  produkt[10];
        int i =0;
        MouseListener listener = new DragMouseAdapter();
        for (produkt label:paletten){
            label = new produkt(true);
            paletten[i]=label;
            int x;
            if (i<5) {
                x =100;
            } else {
                x=420;
            }
            int y = -30+(i%5)*170;
            paletten[i].setBounds(x,y,183,140); //calculates position so it calculates everything
            paletten[i].addMouseListener(listener);
            paletten[i].setTransferHandler(new TransHandler(this,lager));
            this.add(paletten[i]);
            i++;
        }

        //Teststuff
        produkt test2 =new produkt(produkttyp.Stein,produkttyp.Granit,produkttyp.leicht);
        test2.setBounds(paletten[6].getBounds());
        this.remove(paletten[6]);
        this.validate();
        this.repaint();
        paletten[6] = test2;
        paletten[6].addMouseListener(listener);
        paletten[6].setTransferHandler(new TransHandler(this,lager));
        short[] punkt =  {1,3,0};
        lager.einlagern(punkt,paletten[6]);
        lager.lager_ausgeben();
        this.add(test2);

    }
    private class DragMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
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
    public void update_regal(){
        produkt[][][] to_update = lager.getLager();
        var listen = new DragMouseAdapter();
        for (int i = 0;i<5;i++){
            for (int j = 0;j<2;j++){
                int numb = -1;
                if (j==0) {
                    switch (i) {
                        case 0: numb = 4; break;
                        case 1: numb = 3; break;
                        case 2: numb = 2; break;
                        case 3: numb = 1; break;
                        case 4: numb = 0;break;
                    }
                } else {
                    switch (i) {
                        case 0: numb = 9; break;
                        case 1: numb = 8; break;
                        case 2: numb = 7; break;
                        case 3: numb = 6; break;
                        case 4: numb = 5;break;
                    }
                }
                System.out.print(i);
                System.out.print("|");
                System.out.println(j);
                Rectangle rec= paletten[numb].getBounds();
                this.remove(paletten[numb]);
                paletten[numb]=null;
                if (to_update[j][i][0]!=null) {
                    paletten[numb] =to_update[j][i][0];
                    ImageIcon t =new ImageIcon(paletten[numb].getPath());
                    t.setImage(t.getImage().getScaledInstance(183,140,1));
                    paletten[numb].setIcon(t);
                    paletten[numb].setBounds(rec);
                    paletten[numb].addMouseListener(listen);
                    paletten[numb].setTransferHandler(new TransHandler(this,lager));
                    this.add(paletten[numb]);
                } else if (to_update[j][i][1]!=null) {

                }else {
                    to_update[j][i][0] = new produkt(true);
                    paletten[numb] =to_update[j][i][0];
                    paletten[numb].setBounds(rec);
                    paletten[numb].addMouseListener(listen);
                    paletten[numb].setTransferHandler(new TransHandler(this,lager));
                    this.add(paletten[numb]);
                }

            }
        }
        this.validate();
        this.repaint();
    }
}