package controller.tecnical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.TiendaInf;
import view.TecnicalJDialog;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class TecnicalController {

    private TecnicalJDialog view;
    private TiendaInf model;

    public TecnicalController(TecnicalJDialog view, TiendaInf model) {
        this.model = model;
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
