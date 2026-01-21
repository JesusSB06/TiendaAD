package controller.loginRegister;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import controller.register.RegisterController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.OperationsDB;
import model.TiendaInf;
import view.LoginRegisterJDialog;
import view.MainJFrame;
import view.RegistrarseJDialog;

/**
 *
 * @author invitado
 */
public class LoginRegisterController {

    private LoginRegisterJDialog view;
    private MainJFrame mainView;
    private TiendaInf model;
    private OperationsDB modelOperaciones = new OperationsDB();

    public LoginRegisterController(LoginRegisterJDialog view, TiendaInf model) {
        this.model = model;
        this.view = view;
        changeImage();
        this.view.addSaveJButtonActionListener(this.addSaveJButtonActionListener());
        this.view.addCancelJButtonActionListener(this.addCancelJButtonActionListener());
    }

    public void changeImage() {
        ImageIcon image = new ImageIcon(getClass().getResource("/imagenes/usuario.png"));
        view.getImagetoLabel(image);
    }

    public ActionListener addSaveJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nombre_introducido = view.getUserNameJTextField();
                String contrasenha_introducida = view.getPasswordJPasswordField();

                System.out.println(nombre_introducido);
                System.out.println(contrasenha_introducida);

                try {
                    String inicioSesion = modelOperaciones.usuarioInicioSesion(nombre_introducido, contrasenha_introducida);
                    if (inicioSesion.equals("inicio")) {
                        JOptionPane.showMessageDialog(view, "Inicio de sesión realizado", "Inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
                    } else if (inicioSesion.equals("registrarse")) {
                        int opcion = JOptionPane.showConfirmDialog(view, "El usuario no está en la base de datos \n ¿Desea Registrarse?", "¿Registrarse?", JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION);
                        System.out.println(opcion);
                        if (opcion == 0) {
                            RegistrarseJDialog rjd = new RegistrarseJDialog(mainView, true);
                            RegisterController rgc = new RegisterController(rjd);
                            rjd.setVisible(true);
                            view.setUserNameJTextField("");
                            view.setPasswordJPasswordFiel("");

                        }
                    
                    } else if (inicioSesion.equals("noContrasenha")) {
                        JOptionPane.showMessageDialog(view, "La contraseña introducida no es válida", "Error", JOptionPane.ERROR_MESSAGE);

                    }

                } catch (SQLException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        };
        return al;
    }

    public ActionListener addCancelJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                view.dispose();
            }
        };
        return al;
    }

}
