package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public String usuarioInicioSesion(String nombre_introducido, String contrasenha_introducida) throws SQLException {
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

            if (nombre_cliente.equals(nombre_introducido) && contrasenha_introducida.equals(password)) {
                return "inicio";
            } else if (!nombre_cliente.equals(nombre_introducido)) { 
                return "registrarse";
            }else if(!contrasenha_introducida.equals(password)){
                return "noContrasenha";
            }
        }
        return "no inicio";
    }

    public int anhadirCliente(Client cliente) throws SQLException {
        String dni = cliente.getDni();
        String nombre_cliente = cliente.getNombre_cliente();
        String correo_electronico = cliente.getCorreo_electronico();
        String telefono = cliente.getTelefono();
        String contrasenha = cliente.getContrasenha();
        String insert = "INSERT INTO cliente(dni, nombre_cliente, correo_electronico, telefono, contrasenha) VALUES (?,?,?,?,?)";
        int resultado;
        try (PreparedStatement anhadir = conexion.prepareStatement(insert)) {
            anhadir.setString(1, dni);
            anhadir.setString(2, nombre_cliente);
            anhadir.setString(3, correo_electronico);
            anhadir.setString(4, telefono);
            anhadir.setString(5, contrasenha);
            resultado = anhadir.executeUpdate();
        }
        return resultado;
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

}
