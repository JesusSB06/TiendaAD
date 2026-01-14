package controller;

import controller.loginRegister.LoginRegisterController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import view.LoginRegisterJDialog;
import view.MainJFrame;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class MainController {

    private MainJFrame view;

    public MainController(MainJFrame view) {
        this.view = view;
        this.view.addLoginActionListener(this.getLoginActionListener());

    }

    public void initComponents() {

    }

    public void changeImage() {
        ImageIcon image = new ImageIcon(getClass().getResource("/imagenes/logo1.png"));
        view.getImagetoLabel(image);
    }

    public ActionListener getLoginActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LoginRegisterJDialog lrg = new LoginRegisterJDialog(view, true);
                LoginRegisterController lrc = new LoginRegisterController(lrg);
                lrg.setVisible(true);

            }
        };
        return al;
    }
}
