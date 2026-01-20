package controller.tecnical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.TecnicalJDialog;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class TecnicalController {

    private TecnicalJDialog view;

    public TecnicalController(TecnicalJDialog view) {
        this.view = view;
        this.view.addFixButtonActionListener(this.getFixButtonActionListener());
        this.view.enableFixButton(false);
    }
    
    public ActionListener getFixButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(view, "Reparado el producto con exito");
                
            }
        };
        return al;
    }
}
