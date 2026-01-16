package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import main.main;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class OperationsDB {

    private static Connection conexion = null;

    public static void openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BDTIENDAINFORMATICA";
            conexion = DriverManager.getConnection(url, "root", "abc123.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.getLogger(main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static void closeConnection() throws SQLException {
        conexion.close();
    }
}
