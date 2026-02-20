package com.mycompany.psp.proyectud3;

import com.mycompany.psp.proyectud3.client.ClientImpl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.mycompany.psp.proyectud3.client.IClient;
import java.rmi.RemoteException;

/**
 *
 * @author jsbje
 */
public class Server {

    public static void main(String[] args) {
        int port = 1099;
        String bindName = "Chat";
        try {
            Registry registry;
            try {
                registry = LocateRegistry.getRegistry(port);
                System.out.println("Se ha encontrado el registry en el puerto " + port);
                IClient service = new ClientImpl();
                registry.rebind(bindName, service);
            } catch (RemoteException e) {
                System.out.println("Registry no encontrado");
            }

            System.out.println("Servicio publicado como: " + bindName);

        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
