/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.psp.proyectud3.controller;

import com.mycompany.psp.proyectud3.client.Client;
import com.mycompany.psp.proyectud3.view.UserDialog;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.mycompany.psp.proyectud3.client.IClient;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Vector;
import javax.swing.SwingUtilities;
import com.mycompany.psp.proyectud3.client.ClientCallback;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author jsbje
 */
public class UserController extends UnicastRemoteObject implements ClientCallback{
    private UserDialog view;
    private Client client;
    private IClient server;

    public UserController(UserDialog view, Client client, IClient server) throws RemoteException {
        this.view = view;
        this.client = client;
        this.server = server;
        this.view.setSendButtonActionListener(this.setSendMessageButtonActionListener());
        this.view.setUserTableListSelectionListener(this.setUserTableListSelectionListener());
        server.registerClient(client, this);
    }

    @Override
    public void updateUserList(String[] users) throws RemoteException {
        SwingUtilities.invokeLater(() -> {
            view.clearTable(view.getUsersTable()); 
            for (String user : users) {
                if (!user.equals(client.getName())) {
                    Vector row = new Vector();
                    row.add(user);
                    view.addRowTable(row, view.getUsersTable());
                }
            }
        });
    }
    
    private ListSelectionListener setUserTableListSelectionListener(){
        ListSelectionListener lsl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    int rowUser = view.getSelectedRow(view.getUsersTable());
                    if(rowUser != -1){
                        String user = view.getUsersTable().getModel().getValueAt(rowUser, 0).toString();
                        view.clearTable(view.getMessageTable());
                        List<String> messages = client.getMessagesFrom(user);
                        for(String st : messages){
                            Vector rowTable = new Vector();
                            rowTable.add(st);
                            rowTable.add("");
                            view.addRowTable(rowTable, view.getMessageTable());
                        }
                    }
                    
                }
            }
        };
        return lsl;
    }

    private ActionListener setSendMessageButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = view.getMessageText().trim();
                if (!message.isEmpty()) {
                    if (view.getSelectedRow(view.getUsersTable()) != -1) {
                        try {
                            server.sendMessage(client.getName(), message);
                            Vector row = new Vector();
                            row.add("");
                            row.add(message);
                            view.addRowTable(row, view.getMessageTable());
                        } catch (RemoteException ex) {
                            JOptionPane.showMessageDialog(view, "Error al enviar el mensaje", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(view, "Error al enviar el mensaje, no existe usuario", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        };
        return al;
    }

    @Override
    public void receiveMessage(Client sender, String message) throws RemoteException {
        client.addMessage(sender.getName(), message);
        SwingUtilities.invokeLater(() -> {
            int rowUser = view.getSelectedRow(view.getUsersTable());
            if (rowUser != -1) {
                Vector row = new Vector();
                row.add(message);
                row.add("");
                view.addRowTable(row, view.getMessageTable());
            }

        });
    }

}
