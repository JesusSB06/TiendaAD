package controller;

import controller.asistant.AsistantController;
import controller.loginRegister.LoginRegisterController;
import controller.client.ClientController;
import controller.tecnical.TecnicalController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Asistant;
import model.Supervisor;
import model.Technician;
import model.TiendaInf;
import view.AsistantJDialog;
import view.LoginRegisterJDialog;
import view.MainJFrame;
import view.ClientsJDialog;
import view.TecnicalJDialog;

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
                if (model.getClient() != null && model.getEmployee() == null) {
                    ClientsJDialog pjd = new ClientsJDialog(view, true);
                    ClientController pc = new ClientController(pjd, model);
                    pjd.setVisible(true);
                }
                else if (model.getClient() == null && model.getEmployee() != null){
                    if(model.getEmployee() instanceof Technician){
                        TecnicalJDialog td = new TecnicalJDialog(view,true);
                        TecnicalController tc = new TecnicalController(td,model);
                        td.setVisible(true);
                    }
                    else if(model.getEmployee()instanceof Asistant){
                        AsistantJDialog ad = new AsistantJDialog(view,true);
                        AsistantController ac = new AsistantController(ad,model);
                        ad.setVisible(true);
                    }
                    else if(model.getEmployee() instanceof Supervisor){
                        
                    }
                }else{
                    JOptionPane.showMessageDialog(view, "Error crítico en la aplicacion, reinicio requerido");
                }
            }
        };
        return al;

    }
}
