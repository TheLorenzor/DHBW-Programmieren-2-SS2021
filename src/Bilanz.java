import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

public class Bilanz extends JFrame {
    private JTable anzeigen;
    public long summe;
    public long  einnahmen;
    public long ausgaben;
    private mymodel data;
    private  JLabel summe_label;
    private JLabel einnahmen_label;
    private  JLabel ausgaben_label;

    public Bilanz() {
        super("Detaillierte Bilanzanzeige");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLayout(null);

        this.summe =0;
        this.ausgaben=0;
        this.einnahmen =0;

        anzeigen  = new JTable();
        data = new mymodel();
        anzeigen.setModel(data);
        JScrollPane table_container = new JScrollPane(anzeigen);
        JPanel auflistung = new JPanel();
        auflistung.add(table_container);
        auflistung.setBounds(0,0,this.getWidth(),400);
        this.add(auflistung);


        JPanel kulminiert = new JPanel();
        kulminiert.setBounds(0,400,this.getWidth(),100);
        kulminiert.setLayout(null);

        this.summe_label= new JLabel("Summe: "+this.summe+"€");
        this.summe_label.setOpaque(true);
        this.summe_label.setBounds(12,10,150,30);
        kulminiert.add(this.summe_label);

        this.einnahmen_label = new JLabel("Umsatz: "+this.einnahmen+"€");
        this.einnahmen_label.setOpaque(true);
        this.einnahmen_label.setBackground(Color.decode("#65ed09"));
        this.einnahmen_label.setBounds(164,10,150,30);
        kulminiert.add(this.einnahmen_label);

        this.ausgaben_label = new JLabel("Kosten: "+this.ausgaben+"€");
        this.ausgaben_label.setOpaque(true);
        this.ausgaben_label.setBackground(Color.decode("#ed4e09"));
        this.ausgaben_label.setBounds(326,10,150,30);
        kulminiert.add(this.ausgaben_label);
        this.add(kulminiert);
        ImageIcon bilanz_icon = new ImageIcon(getClass().getResource("icon_bilanz.png"));
        this.setIconImage(bilanz_icon.getImage());
    }
    private class mymodel extends AbstractTableModel {
        Vector<String> columns ;
        Vector<Vector<String>> data;
        private final String[] column_names = {"Umsätze","Beschreibung","Details"};
        public mymodel(){
            data = new Vector<>();
        }
        @Override
        public int getRowCount() {
            return data.size();
        }
        @Override
        public String getColumnName(int column){
            return column_names[column];
        }
        @Override
        public boolean isCellEditable(int row, int column ){
            return false;
        }
        @Override
        public int getColumnCount() {
            return column_names.length;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data.get(rowIndex).get(columnIndex);
        }
        public void addRow(Vector<String> new_buch){
            data.add(new_buch);
            int row = data.indexOf(new_buch);
            for (int column=0; column<new_buch.size();column++){
                fireTableCellUpdated(row,column);
            }
            fireTableRowsInserted(row,row);
        }
    }

    public void update_nonTable(){
        this.ausgaben_label.setText("Kosten: "+this.ausgaben+"€");
        this.einnahmen_label.setText("Umsatz: "+this.einnahmen+"€");
        this.summe_label.setText("Summe: "+this.summe+"€");
        this.validate();
        this.repaint();
    }


   public void add_Buchung(bilanztyp type,short[]from,short[]to){
        if (type!=bilanztyp.auftrag&&type!=bilanztyp.auftrag_delete){
            Vector<String> row = new Vector<>();
            switch (type){
                case reaarange:
                    row.add("-50€");
                    row.add("Verschiebung Palette");
                    row.add("Von: "+from[0]+"|"+from[1]+"|"+from[2]+" nach:"+to[0]+"|"+to[1]+"|"+to[2]);
                    this.data.addRow(row);
                    this.summe = this.summe-50;
                    this.ausgaben = this.ausgaben+50;
                    break;
                case destroy:
                    row.add("-300€");
                    row.add("Verschrottung Palette");
                    row.add("Palette an der Stelle: "+from[0]+"|"+from[1]+"|"+from[2]);
                    this.data.addRow(row);
                    this.summe = this.summe-300;
                    this.ausgaben = this.ausgaben+300;
                    break;
            }
            update_nonTable();
        }
   }
    public void add_Buchung(bilanztyp type,auftrag com){
        Vector<String> row = new Vector<>();
        if (type==bilanztyp.auftrag){
            row.add("+"+com.getReward()+"€");
            String desc;
            if (com.get_einlag()){
                desc = "Einlagerung";
            }else {
                desc="Auslagerung";
            }
            row.add(desc);
            row.add("Palette: "+com.getEigenschaften()[0]+" "+com.getEigenschaften()[1]+" "+com.getEigenschaften()[2]);
            this.data.addRow(row);
            this.summe = this.summe+com.getReward();
            this.einnahmen = this.einnahmen+ com.getReward();
        } else if (type==bilanztyp.auftrag_delete) {
            row.add("-"+com.getReward()+"€");
            row.add("Ablehnung Auftrag");
            String desc;

            if (com.get_einlag()){
                desc = "Einlagerung";
            }else {
                desc="Auslagerung";
            }
            row.add("Abgehlenter "+desc+"sauftrag: "+com.getEigenschaften()[0]+" "+com.getEigenschaften()[1]+" "+com.getEigenschaften()[2]);
            this.data.addRow(row);
            this.summe = this.summe-com.getReward();
            this.ausgaben=this.ausgaben+com.getReward();
        }
        update_nonTable();
    }
}
