public class testing {
    public static void main(String[] args){
        Lager lager = new Lager();
        produkt pape = new produkt(produkttyp.Papier,produkttyp.Gruen,produkttyp.A3);
        short[] punkt = {0,0,0};
        lager.einlagern(punkt,pape);
        lager_ausgeben(lager);
        System.out.println("#################################");
        lager.move_palette(punkt,'x',(short)1);
        lager.einlagern(punkt,pape);
        lager_ausgeben(lager);


    }
    public static void lager_ausgeben(Lager lager) {
        produkt[][][] t = lager.getLager();
        for (int i=0;i<t.length;i++) {
            for (int j=0;j<t[i].length;j++) {
                for (int k=0;k<t[i][j].length;k++) {
                    System.out.print(i);
                    System.out.print(j);
                    System.out.print(k);
                    System.out.print(" ");
                    System.out.print( t[i][j][k]);
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
    }
}
