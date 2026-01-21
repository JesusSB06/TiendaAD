package main;

import controller.MainController;
import java.sql.SQLException;
import model.OperationsDB;
import model.TiendaInf;
import view.MainJFrame;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class main {
    private static OperationsDB conexion = new OperationsDB();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        TiendaInf model = new TiendaInf();
        conexion.openConnection();
        conexion.consultaProducto();
        conexion.consultaCliente();
        
        MainJFrame mainJFrame = new MainJFrame();
        mainJFrame.setVisible(true);
        MainController mc = new MainController(mainJFrame, model);
        mc.changeImage();
        
        
    }
    
}
