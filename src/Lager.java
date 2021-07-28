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
                    if (from[2]==0 && to[2]==1) {
                        lager[to[0]][to[1]][to[2]].change_size(true);
                    } else if (from[2]==1 && to[2]==0){
                        lager[to[0]][to[1]][to[2]].change_size(false);
                    }
                    return 0;
                }
            }

        }catch (InvalidMovementException IME) {
            System.err.println("Invalid "+IME.fail+" Parameters");
            return -1;
        }catch (ArrayIndexOutOfBoundsException index){
            System.err.println("Movement außerhalb des Lagers");
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
                lager[punkt[0]][punkt[1]][0] = typ;
            }
            return true;
        } else {
            return false;
        }


    }
    public boolean auslagern(short[] punkt) {
        if (punkt[0]>1||punkt[0]<0||punkt[1]>4||punkt[1]<0||punkt[2]>1||punkt[2]<0) {
            return false;
        }
        if (lager[punkt[0]][punkt[1]][punkt[2]].getEigen()==produkttyp.Balken) {
            lager[punkt[0]][punkt[1]][0] =null;
            lager[punkt[0]][punkt[1]][1] =null;
        } else {
            lager[punkt[0]][punkt[1]][punkt[2]]=null;
        }

        return true;


    }
    public produkt[][][] getLager() {
        return lager;
    }

    public boolean check_movement(produkt t,short[] to,short[] from){
        if(t.getEigen()==produkttyp.Balken) { //if it is balken it needs to check both places
            return lager[to[0]][to[1]][0] == null && lager[to[0]][to[1]][1] == null;
        } else {
            if (lager[to[0]][to[1]][to[2]]!=null) { //if it is not freed it is not posible to move or insert
                return false;
            }
            if(from[2]==1 && lager[from[0]][from[1]][0]!=null) {
                return false;
            }
            if(t.getType()==produkttyp.Stein) {
                if(t.getEigen()==produkttyp.schwer && to[1]>0){
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
    public short[] get_point(produkt find) {
        long id =find.id;
        for (short i=0;i<2;i++) {
            for (short j=0;j<5;j++) {
                for (short k=0;k<2;k++) {
                    if (lager[i][j][k]!=null && lager[i][j][k].id==id) {
                        return new short[]{i,j,k};
                    }
                }
            }
        }
        return new short[]{-1,-1,-1};
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
