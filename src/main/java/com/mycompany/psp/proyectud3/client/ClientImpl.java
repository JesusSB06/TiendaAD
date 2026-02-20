/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.psp.proyectud3.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jsbje
 */
public class ClientImpl extends UnicastRemoteObject implements IClient {

    private Map<String, Client> clients;
    private Map<String, ClientCallback> callbacks;

    public ClientImpl() throws RemoteException {
        clients = new HashMap<>();
        callbacks = new HashMap<>();
    }

    @Override
    public void registerClient(Client client, ClientCallback callback) throws RemoteException {
        clients.put(client.getName(), client);
        callbacks.put(client.getName(), callback);

        notifyUsers();
    }

    private void notifyUsers() throws RemoteException {
        String[] userList = clients.keySet().toArray(new String[0]);
        for (ClientCallback cb : callbacks.values()) {
            try {
                cb.updateUserList(userList);
            } catch (RemoteException e) {
                System.out.println("Error notificando cliente: " + e.getMessage());
            }
        }
    }

    @Override
    public Map<String, Client> getClients() throws RemoteException {
        return clients;
    }

    @Override
    public boolean clientExist(String name) throws RemoteException {
        return clients.containsKey(name);
    }

    @Override
    public Client getClient(String name) throws RemoteException {
        return clients.get(name);
    }

    @Override
    public boolean AddClient(String name) throws RemoteException {
        if (!clientExist(name)) {
            clients.put(name, new Client(name));
            notifyUsers();
            return true;
        }
        return false;
    }

    @Override
public void sendMessage(String from, String message) throws RemoteException {
        Client sender = clients.get(from);
        
        if (sender != null) {
            for (Map.Entry<String, ClientCallback> entry : callbacks.entrySet()) {
                if (!entry.getKey().equals(from)) {
                    try {
                        entry.getValue().receiveMessage(sender, LocalDate.now() + " " + message);
                    } catch (RemoteException e) {
                        System.out.println("Error enviando mensaje a cliente " + entry.getKey() + ": " + e.getMessage());
                    }
                }
            }
        }
    }
}
