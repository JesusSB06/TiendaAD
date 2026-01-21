package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
    public static List<Product> obtenerProductos() throws SQLException{
        List<Product> products = new ArrayList<>();
        String select = "SELECT * from producto";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()) {
            if(rs.getString(4).equals("disponible")){
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getInt(6));
                setImageProducts(p, products);
            }
        }
        st.close();                
        rs.close();

        return products;
    }

    private static void setImageProducts(Product p, List<Product> products) {
        switch (p.getCategory()) {
            case 1 ->
                p.setImg("/imagenes/portatil.png");
            case 2 ->
                p.setImg("/imagenes/computadora.png");
            case 3 ->
                p.setImg("/imagenes/perifericos.png");
            case 4 ->
                p.setImg("/imagenes/componentes.jpeg");
            case 5 ->
                p.setImg("/imagenes/accesor.png");
            case 6 ->
                p.setImg("/imagenes/red.png");
            case 7 ->
                p.setImg("/imagenes/almacenamiento.png");
            default -> {

            }

        }
        products.add(p);
    }

    private static List<Product> ThreadSearch(String value) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * from producto WHERE nombre_producto LIKE '" + value + "%'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            if (rs.getString(4).equals("disponible")) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getInt(6));
                setImageProducts(p, products);
            }
        }
        st.close();
        rs.close();

        return products;
    }

    public void consultaCliente() throws SQLException {
        System.out.println("Consulta producto");
        String select = "SELECT * from cliente";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()) {
            System.out.println("-- Clientes --");
            System.out.println("DNI: " + rs.getString("dni"));
            System.out.println("Nombre: " + rs.getString("nombre_cliente"));
            System.out.println("Correo: " + rs.getString("correo_electronico"));
            System.out.println("Telefono: " + rs.getString("telefono"));
            System.out.println("Contrasenha: " + rs.getString("contrasenha"));

        }
    }

    public boolean usuarioInicioSesion(String nombre_introducido, String contrasenha_introducida) throws SQLException {
        String select = "SELECT nombre_cliente, contrasenha FROM cliente";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()) {
            String nombre_cliente = rs.getString("nombre_cliente");
            String password = rs.getString("contrasenha");
            System.out.println("===================================");
            System.out.println("nombre esperado: " + nombre_cliente);
            System.out.println("password esperado: " + password);
            System.out.println("===================================");

            if (nombre_cliente.contains(nombre_introducido) && contrasenha_introducida.contains(contrasenha_introducida)) {
                return true;
            } else if (!nombre_cliente.contains(nombre_introducido) || !contrasenha_introducida.contains(password)) {
                return false;
            }
        }
        return false;
    }

    public int addProduct(Product product) throws SQLException {
        int vId = product.getId();
        String vName = product.getName();
        int vStock = product.getStock();
        String vState = product.getState();
        Double vPrice = product.getPrice();
        String vImg = product.getImg();
        int vCategory = product.getCategory();
        String sentenciaSQL = "INSERT into Product (id_producto, nombre_producto, stock, estado, price, img, category) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(sentenciaSQL);
        int resultado = ps.executeUpdate();
        ps.close();
        return resultado;
    }
    


}
