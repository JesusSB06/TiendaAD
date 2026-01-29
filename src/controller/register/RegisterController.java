/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Client;
import model.OperationsDB;
import model.TiendaInf;
import view.RegistrarseJDialog;

/**
 *
 * @author alumno
 */
public class RegisterController {

    private RegistrarseJDialog view;
    private TiendaInf model;

    public RegisterController(RegistrarseJDialog view, TiendaInf model) {
        this.view = view;
        this.model = model;
        this.view.addSaveJButtonActionListener(this.getSaveJButtonActionListener());
        this.view.addCancelJButtonActionListener(this.getCancelJButtonActionListener());
    }

    public ActionListener getSaveJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = view.getDNIJTextField();
                String nombre_cliente = view.getNameJTextField();
                String correo_electronico = view.getEmailJTextField();
                String telefono = view.getTelefonoJTextField();
                Double saldo = view.getSpinnerValue();
                String contrasenha = view.getPasswordJField();
                Client nuevoCliente = new Client(dni, nombre_cliente, correo_electronico,  telefono, saldo, contrasenha);
                model.setClient(nuevoCliente);
                try {
                    if (dni.isEmpty() || nombre_cliente.isEmpty() || correo_electronico.isEmpty() || telefono.isEmpty() || contrasenha.isEmpty()) {
                        JOptionPane.showMessageDialog(view, "Algún campo está vacio, introduzca todos los datos", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
                    }else {
                        OperationsDB.anhadirCliente(nuevoCliente);
                        model.setClient(nuevoCliente);
                        JOptionPane.showMessageDialog(view, "Usuario introducido", "Usuario creado correctamente", JOptionPane.INFORMATION_MESSAGE);
                        view.dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "DNI introducido ya existe en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        return al;
    }

    public ActionListener getCancelJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        };
        return al;
    }

}
