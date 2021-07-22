import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Bilanz extends JFrame {
    public long geld;
    List<Array> bilanzen;
    public Bilanz() {
        super("Detaillierte Bilanzanzeige");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        bilanzen = new ArrayList<>();
    }
}
