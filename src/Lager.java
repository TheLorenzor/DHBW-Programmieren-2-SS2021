public class Lager {
    private produkt[][][] lager;
    public Lager(){
        lager = new produkt[2][5][2];

    }
    /*
    * erst achse --> x,y,z --> und minus 1 oder plus eins um es zu verschieben
    * */
    public boolean move_palette(String axis,short mov){

        return true;
    }

    public produkt[][][] getLager() {
        return lager;
    }
}
