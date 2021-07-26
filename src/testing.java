public class testing {
    public static void main(String[] args){
        auftrag[] auftrage = new auftrag[50];
        for (int i =0;i<50;i++){
            auftrage[i]=new auftrag();
        }
        System.out.println(auftrage[0].getEigenschaften()[1]);
        System.out.println(auftrage[1].getEigenschaften()[1]);
        System.out.println(auftrage[2].getEigenschaften()[1]);

    }
}
