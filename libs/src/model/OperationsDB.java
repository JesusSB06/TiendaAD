package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import main.main;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class OperationsDB {

    private static Connection conexion;

    public void openConnection() {
        try {
            System.out.println("Abriendo conexion");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/tienda_inf";
            conexion = DriverManager.getConnection(url, "root", "abc123.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.getLogger(main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void closeConnection() throws SQLException {
        conexion.close();
    }

    public void consultaProducto() throws SQLException {
        System.out.println("Consulta producto");
        String select = "SELECT * from producto";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()) {
            System.out.println("-- Producto --");
            System.out.println("ID: " + rs.getString("id_producto"));
            System.out.println("Nombre: " + rs.getString("nombre_producto"));
            System.out.println("Stock: " + rs.getInt("stock"));
            System.out.println("Estado: " + rs.getString("estado"));
            System.out.println("Precio: " + rs.getDouble("precio"));
            System.out.println("Categoria: " + rs.getString("categoria"));

        }
    }

    public int addProduct(Product product) throws SQLException {
        int vId = product.getId();
        String vName = product.getName();
        int vStock = product.getStock();
        String vState = product.getState();
        Double vPrice = product.getPrice();
        String vImg = product.getImg();
        Category vCategory = product.getCategory();
        String sentenciaSQL = "INSERT into Product (id_producto, nombre_producto, stock, estado, price, img, category) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(sentenciaSQL);
        int resultado = ps.executeUpdate();
        ps.close();
        return resultado;
    }
    
    public void modifyProduct(int id, String campo, String valor){
        String sql = "";
    }
}
