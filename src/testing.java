public class testing {
    public static void main(String[] args){
        Lager lager = new Lager();
        produkt pape = new produkt(produkttyp.Papier,produkttyp.Gruen,produkttyp.A3);
        produkt balken = new produkt(produkttyp.Holz,produkttyp.Buche,produkttyp.Balken);
        short[] punkt = {0,0,0};
        lager.einlagern(punkt,pape);
        lager.lager_ausgeben();
        punkt[1]=1;
        lager.einlagern(punkt,balken);
        System.out.println("#################################");
        lager.lager_ausgeben();
        lager.auslagern(punkt);
        lager.lager_ausgeben();
    }
}
