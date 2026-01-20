package controller.loginRegister;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.OperationsDB;
import view.LoginRegisterJDialog;

/**
 *
 * @author invitado
 */
public class LoginRegisterController {

    private LoginRegisterJDialog view;
    private OperationsDB modelOperaciones = new OperationsDB();

    public LoginRegisterController(LoginRegisterJDialog view) {
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
                    boolean inicioSesion = modelOperaciones.usuarioInicioSesion(nombre_introducido, contrasenha_introducida);
                    if (inicioSesion == true) {
                        JOptionPane.showMessageDialog(view, "Inicio de sesión realizado", "Inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
                    } else if (inicioSesion == false) {
                        JOptionPane.showMessageDialog(view, "Contraseña o usuario incorrecto", "Inicio de sesión", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (SQLException ex) {
                    System.getLogger(LoginRegisterController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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
