/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.psp.proyectud3.client;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author jsbje
 */
public interface IClient extends Remote {
    void sendMessage(String from, String message) throws RemoteException;
    void registerClient(Client client, ClientCallback callback) throws RemoteException; // cambiamos
    boolean clientExist(String name) throws RemoteException;
    Client getClient(String name) throws RemoteException;
    boolean AddClient(String name) throws RemoteException;
    Map<String, Client> getClients() throws RemoteException;
} 
