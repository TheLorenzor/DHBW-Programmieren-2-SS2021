import javax.swing.*;

public class Bilanz extends JFrame {
    public long geld;
    public Bilanz() {
        super("Detaillierte Bilanzanzeige");
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
}
