import javax.swing.*;
import java.awt.datatransfer.Transferable;

public class TransHandler extends TransferHandler {
    private regal check;
    private Lager lager;
    public TransHandler(regal reg,Lager store){
        super("icon");
        this.check = reg;
        this.lager = store;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        try {
            produkt t = (produkt) source;
            if (t.getType()==null) {
                action =0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (action==1) {
            lager.lager_ausgeben();
        }
        check.update_regal();
    }
}
