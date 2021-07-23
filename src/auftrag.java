import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class auftrag {
    private Integer id;
    private boolean in_out; //in equals true
    private int reward;
    private produkttyp type;
    private produkttyp eig;
    private produkttyp eig2;

    private static int nr;

    public auftrag(){
        URL path_to_csv = getClass().getResource("Leistungsnachweis.csv");
        System.out.println(path_to_csv.toString());
        String[] data={};
        try {
            BufferedReader csvread = new BufferedReader(new FileReader(path_to_csv.toString()));
            int i=0;
            do {
                data = null;
                if (nr==0){
                    csvread.readLine();
                    nr=2;
                } else {
                    String row= csvread.readLine();
                    if (row ==null) {
                        break;
                    } else {
                        i++;
                        data = row.split(";");
                    }
                }

            } while(i<nr);
            ++nr;
            csvread.close();
        } catch (FileNotFoundException fe) {
            System.out.println("file nicht gefunden, bitte neues eingeben");
        } catch (IOException io) {
            System.out.println("Eingabefehler");
        }
        if (data.length!=0){
            id = Integer.parseInt(data[0]);
            in_out= data[1].equals("Einlagerung");
            reward = Integer.parseInt(data[5]);
            if(data[2].equals("Holz")) {
                type = produkttyp.Holz;
            }else if(data[2].equals("Papier")){
                type = produkttyp.Papier;
            }else {
                type = produkttyp.Stein;
            }
            switch (data[3]) {
                case "Wei�" -> eig = produkttyp.Weis;
                case "Grün" -> eig = produkttyp.Gruen;
                case "Blau" -> eig = produkttyp.Blau;
                case "Kiefer" -> eig = produkttyp.Kiefer;
                case "Buche" -> eig = produkttyp.Buche;
                case "Eiche" -> eig = produkttyp.Eiche;
                case "Marmor" -> eig = produkttyp.Marmor;
                case "Granit" -> eig = produkttyp.Granit;
                case "Sandstein" -> eig = produkttyp.Sandstein;
            }
            switch (data[4]) {
                case "A3" -> eig2 = produkttyp.A3;
                case "A4" -> eig2 = produkttyp.A4;
                case "A5" -> eig2 = produkttyp.A5;
                case "Bretter" -> eig2 = produkttyp.Bretter;
                case "Balken" -> eig2 = produkttyp.Balken;
                case "Scheit" -> eig2 = produkttyp.Scheit;
                case "Leicht" -> eig2 = produkttyp.leicht;
                case "Mittel" -> eig2 = produkttyp.mittel;
                case "Schwer" -> eig2 = produkttyp.schwer;
            }

        }
    }

    public produkttyp[] getEigenschaften() {
        return new produkttyp[]{type,eig,eig2};
    }
    public boolean get_einlag(){
        return in_out;
    }
    public int getReward(){
        return reward;
    }
    public int getId(){
        return id;
    }

}
