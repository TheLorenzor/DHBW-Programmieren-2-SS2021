import java.lang.Math;
public class Lager {
    private produkt[][][] lager;
    public Lager(){
        lager = new produkt[2][5][2];

    }
    public int move_palette(short[] from,short[] to){
        try {
            if (from[0]<0||from[0]>1||from[1]<0||from[1]>4||from[2]<0||from[2]>1) { //if it is out of bounce
                throw new ArrayIndexOutOfBoundsException();
            }
            if (to[0]<0||to[0]>1||to[1]<0||to[1]>4||to[2]<0||to[2]>1) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (Math.abs(from[0]-to[0])+Math.abs(from[1]-to[1])+Math.abs(from[2]-to[2])>1) {
                throw new InvalidMovementException("Input");
            }
            produkt to_move = lager[from[0]][from[1]][from[2]];
            if(to_move.getEigen()==produkttyp.Balken){
                if(check_movement(to_move,to,from)) {
                    lager[to[0]][to[1]][0] = to_move;
                    lager[to[0]][to[1]][1] = to_move;
                    lager[from[0]][from[1]][0] = null;
                    lager[from[0]][from[1]][1] = null;
                    return 0;
                } else {
                    return 1;
                }
            } else {
                if (check_movement(to_move,to,from)) {
                    lager[to[0]][to[1]][to[2]] = to_move;
                    lager[from[0]][from[1]][from[2]] = null;
                    return 0;
                }
            }

        }catch (InvalidMovementException IME) {
            System.out.println("Invalid "+IME.fail+" Parameters");
            return -1;
        }catch (ArrayIndexOutOfBoundsException index){
            System.out.println("Movement außerhalb des Lagers");
            return -1;
        }
        return 0;
    }

    public boolean einlagern(short[] punkt,produkt typ){
        if(check_movement(typ,punkt,punkt)){
            if (typ.getEigen()==produkttyp.Balken) {
                lager[punkt[0]][punkt[1]][0] =typ;
                lager[punkt[0]][punkt[1]][1] =typ;
            } else {
                lager[punkt[0]][punkt[1]][punkt[2]] = typ;
            }
            return true;
        } else {
            return false;
        }


    }
    public produkt[][][] getLager() {
        return lager;
    }

    private boolean check_movement(produkt t,short[] to,short[] from){
        if(t.getArt()==produkttyp.Balken) { //if it is balken it needs to check both places
            if (to[0]-from[0]!=0 || to[1]-from[1]!=0) { //gets activated if it is being inserted
                if(lager[to[0]][to[1]][0]==null &&lager[to[0]][to[1]][1]==null) { //if both are free it can be noved
                    return true;
                } else {
                    return false;
                }
            } else {
                if(lager[to[0]][to[1]][0]==null &&lager[to[0]][to[1]][1]==null) { //if it is moved the to is beeing activated
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (lager[to[0]][to[1]][to[2]]!=null) { //if it is not freed it is not posible to move or insert
                return false;
            }
            if(from[2]==1 && lager[from[0]][from[1]][0]!=null) {
                return false;
            }
            if(t.getType()==produkttyp.Stein) {
                if(t.getEigen()==produkttyp.schwer && to[1]>1){
                    return false;
                }
                if(t.getEigen()==produkttyp.mittel && to[1]==4) {
                    return false;
                }
            }
        }

        return true;
    }

    private class InvalidMovementException extends Exception {
        String fail;
        public InvalidMovementException(String f){
            super();
            fail = f;
        }
    }


    public void lager_ausgeben() {
        for (int i=0;i<lager.length;i++) {
            for (int j=0;j<lager[i].length;j++) {
                for (int k=0;k<lager[i][j].length;k++) {
                    System.out.print(i);
                    System.out.print(j);
                    System.out.print(k);
                    System.out.print(" ");
                    System.out.print(lager[i][j][k]);
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
    }
}
