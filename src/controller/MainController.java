package controller;

import controller.loginRegister.LoginRegisterController;
import controller.products.ProductsController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import model.TiendaInf;
import view.LoginRegisterJDialog;
import view.MainJFrame;
import view.ProductsJDialog;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class MainController {

    private MainJFrame view;
    private TiendaInf model;

    public MainController(MainJFrame view, TiendaInf model) {
        this.view = view;
        this.model = model;
        initComponents();
        this.view.addLoginJButtonActionListener(this.getLoginJButtonActionListener());
        this.view.addStartJButtonActionListener(this.getStartJButtonActionListener());

    }

    public void initComponents() {
        view.setVisibleStartJButton(false);
    }

    public void changeImage() {
        ImageIcon image = new ImageIcon(getClass().getResource("/imagenes/logo1.png"));
        view.getImagetoLabel(image);
    }

    public ActionListener getLoginJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LoginRegisterJDialog lrg = new LoginRegisterJDialog(view, true);
                LoginRegisterController lrc = new LoginRegisterController(lrg,view, model);
                lrg.setVisible(true);

            }
        };
        return al;
    }

    public ActionListener getStartJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ProductsJDialog pjd = new ProductsJDialog(view, true);
                ProductsController pc = new ProductsController(pjd, model);
                pjd.setVisible(true);
            }
        };
        return al;

    }
}
