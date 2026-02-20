
package com.mycompany.psp.proyectud3.client;

/**
 *
 * @author jsbje
 */
import com.mycompany.psp.proyectud3.client.Client;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void updateUserList(String[] users) throws RemoteException;
    void receiveMessage(Client client, String message)throws RemoteException;
}
