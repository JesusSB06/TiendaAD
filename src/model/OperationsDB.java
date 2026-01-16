package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static int addProduct(Product product) throws SQLException {
        int vId = product.getId();
        String vName = product.getName();
        int vStock = product.getStock();
        String vState = product.getState();
        Double vPrice = product.getPrice();
        String vImg = product.getImg();
        Category vCategory = product.getCategory();
        String sentenciaSQL = "INSERT into Product (id, name, stock, state, price, img, category) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(sentenciaSQL);
        int resultado = ps.executeUpdate();
        ps.close();
        return resultado;
    }
}
