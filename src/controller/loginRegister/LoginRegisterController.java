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

    public LoginRegisterController(LoginRegisterJDialog view, MainJFrame mainView, TiendaInf model) {
        this.model = model;
        this.view = view;
        this.mainView = mainView;
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
                try {
                    String usuario = view.getUserNameJTextField();
                    String contrasenha_introducida = view.getPasswordJPasswordField();
                    if (view.getSelectionComboBox().equalsIgnoreCase("cliente")) {
                        String inicioSesion = OperationsDB.usuarioInicioSesion(usuario, contrasenha_introducida);
                        if (inicioSesion.equals("inicio")) {
                            JOptionPane.showMessageDialog(view, "Inicio de sesión realizado", "Inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
                            model.setClient(OperationsDB.ObtenerCliente(usuario));
                            view.dispose();
                            mainView.setVisibleStartJButton(true);
                        } else if (inicioSesion.equals("registrarse")) {
                            int opcion = JOptionPane.showConfirmDialog(view, "El usuario no está en la base de datos \n ¿Desea Registrarse?", "¿Registrarse?", JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION);
                            System.out.println(opcion);
                            if (opcion == 0) {
                                RegistrarseJDialog rjd = new RegistrarseJDialog(mainView, true);
                                RegisterController rgc = new RegisterController(rjd, model);
                                rjd.setVisible(true);
                                view.setUserNameJTextField("");
                                view.setPasswordJPasswordFiel("");
                            }
                        } else if (inicioSesion.equals("noContrasenha")) {
                            JOptionPane.showMessageDialog(view, "La contraseña introducida no es válida", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        try {
                            int id = Integer.parseInt(usuario);
                            if (OperationsDB.empleadoInicioSesion(id, contrasenha_introducida, view.getSelectionComboBox())) {
                                if (view.getSelectionComboBox().equalsIgnoreCase("técnico")) {
                                    model.setEmployee(OperationsDB.getEmployee(id, "técnico"));

                                } else if (view.getSelectionComboBox().equalsIgnoreCase("asistente")) {
                                    model.setEmployee(OperationsDB.getEmployee(id, "asistente"));
                                }
                                JOptionPane.showMessageDialog(view, "Inicio de sesión realizado", "Inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
                                view.dispose();
                                mainView.setVisibleStartJButton(true);
                            }else{
                                JOptionPane.showMessageDialog(view, "Error: contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(view, "Error: el id es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                        }
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
