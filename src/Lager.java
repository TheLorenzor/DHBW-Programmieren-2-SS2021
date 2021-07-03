public class Lager {
    private produkt[][][] lager;
    public Lager(){
        lager = new produkt[2][5][2];

    }
    /*
    * erst achse --> x,y,z --> und minus 1 oder plus eins um es zu verschieben
    * origin --> [0]-> x / [1]->y / [2]--> z --> wenn -1 --> gesamtes REgalplatz --> wichtig für bretter
    * */
    public boolean move_palette(short[] origin,char axis,short mov){
        try {
            if ((axis != 'x' && axis != 'y' && axis != 'z')||mov>1||mov<-1||origin.length!=3) {
                throw new InvalidMovementException("Input");
            }
            produkt to_move = lager[origin[0]][origin[1]][origin[2]];
            if (to_move.getEigen() == produkttyp.Balken && axis =='z') {
                throw new InvalidMovementException("Movement");
            }
            switch (axis) {
                case 'x':
                    if (lager[origin[0]+mov][origin[1]][origin[2]]==null) {
                        lager[origin[0]+mov][origin[1]][origin[2]] = to_move;
                        lager[origin[0]][origin[1]][origin[2]] = null;
                    }
                case 'y':
                case 'z':
            }
        }catch (InvalidMovementException IME) {
            System.out.println("Invalid "+IME.fail+" Parameters");
            return false;
        }
        return true;
    }
    public boolean einlagern(short[] punkt,produkt typ){
        lager[punkt[0]][punkt[1]][punkt[2]] = typ;
        return true;
    }
    public produkt[][][] getLager() {
        return lager;
    }
    private class InvalidMovementException extends Exception {
        String fail;
        public InvalidMovementException(String f){
            super();
            fail = f;
        }
    }
}
