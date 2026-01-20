package controller.loginRegister;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.LoginRegisterJDialog;

/**
 *
 * @author invitado
 */
public class LoginRegisterController {

    private LoginRegisterJDialog view;

    public LoginRegisterController(LoginRegisterJDialog view) {
        this.view = view;
        this.view.addSaveJButtonActionListener(this.addSaveJButtonActionListener());
        this.view.addCancelJButtonActionListener(this.addCancelJButtonActionListener());
    }

    public ActionListener addSaveJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("SAVE");
            }
        };
        return al;
    }

    public ActionListener addCancelJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("CANCEL");
            }
        };
        return al;
    }

}
