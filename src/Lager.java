public class Lager {
    private produkt[][][] lager;
    public Lager(){
        lager = new produkt[2][5][2];

    }
    /*
    * erst achse --> x,y,z --> und minus 1 oder plus eins um es zu verschieben
    * origin --> [0]-> x / [1]->y / [2]--> z --> wenn -1 --> gesamtes REgalplatz --> wichtig für bretter
    * */
    public boolean move_palette(short[] origin,String axis,short mov){
        try {
            if ((axis.indexOf('x')==-1 && axis.indexOf('y')==-1 && axis.indexOf('z')==-1)||axis.length()>1||mov>1||mov<-1||origin.length!=3) {
                throw new InvalidMovementException("");
            }
            produkt to_move = lager[origin[0]][origin[1]][origin[2]];
        }catch (InvalidMovementException IME) {
            System.out.println("Invalid input Parameters");
            return false;
        }
        return true;
    }

    public produkt[][][] getLager() {
        return lager;
    }
    private class InvalidMovementException extends Exception {
        public InvalidMovementException(String errorMessage){
            super(errorMessage);
        }
    }
}
