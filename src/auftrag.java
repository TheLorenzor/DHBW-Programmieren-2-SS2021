import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class auftrag {
    private Integer id;
    private boolean in_out; //in equals true
    private int reward;
    private produkttyp type;
    private produkttyp eig;
    private produkttyp eig2;

    private static int nr;

    public auftrag(){
        String path_to_csv ="../Assets/";
        String data[];
        try {
            BufferedReader csvread = new BufferedReader(new FileReader(path_to_csv));
            int i=0;
            do {
                if (nr==0){
                    csvread.readLine();
                    nr = 1;
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
            csvread.close();
        } catch (FileNotFoundException fe) {
            System.out.println("file nicht gefunden, bitte neues eingeben");
        } catch (IOException io) {
            System.out.println("Eingabefehler");
        }
        if (data!=null){
            id = Integer.parseInt(data[0]);
            in_out= data[1].equals("Einlagerung");
            if
        }
    }
}
