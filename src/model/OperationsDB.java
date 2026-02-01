package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import main.main;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class OperationsDB {

    private static Connection conexion;

    public static void openConnection() {
        try {
            System.out.println("Abriendo conexion");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/tienda_inf";
            conexion = DriverManager.getConnection(url, "root", "abc123.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.getLogger(main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static void closeConnection() throws SQLException {
        conexion.close();
    }

    public static void consultaProducto() throws SQLException {
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

    public static void consultaCliente() throws SQLException {
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

    public static Client ObtenerCliente(String dni) throws SQLException {
        Client client = null;
        String select = "SELECT * from cliente WHERE dni = ?";
        PreparedStatement st = conexion.prepareStatement(select);
        st.setString(1, dni);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            client = new Client(rs.getString("dni"), rs.getString("nombre_cliente"), rs.getString("correo_electronico"), rs.getString("telefono"),rs.getDouble("saldo"), rs.getString("contrasenha"));
        }
        return client;
    }

    public static String usuarioInicioSesion(String dni, String contrasenha_introducida) throws SQLException {
        boolean usuarioExiste = false;
        String select = "SELECT dni, contrasenha FROM cliente";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()) {
            String nombre_cliente = rs.getString("dni");
            String password = rs.getString("contrasenha");
            if (nombre_cliente.equals(dni)) {
                usuarioExiste = true;

                if (password.equals(contrasenha_introducida)) {
                    return "inicio";
                } else {
                    return "noContrasenha";
                }
            }
        }
        if (!usuarioExiste) {
            return "registrarse";
        }
        return "no inicio";
    }
    

    
    public static boolean empleadoInicioSesion(int id, String password, String type) throws SQLException{
        String sql = "";
        boolean existeUsuario =false;
        if(type.equalsIgnoreCase("técnico")){
            sql = "SELECT empleado.id_empleado, contrasenha FROM empleado JOIN tecnico ON empleado.id_empleado = tecnico.id_empleado";
        }else if (type.equalsIgnoreCase("asistente")){
            sql = "SELECT empleado.id_empleado, contrasenha FROM empleado JOIN asistente ON empleado.id_empleado = asistente.id_empleado";
        }
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            if(rs.getInt("empleado.id_empleado") == id && password.equalsIgnoreCase(rs.getString("contrasenha"))){
                existeUsuario = true;
            }
        }
        return existeUsuario;
    }

    public static Employee getEmployee(int id_empleado, String type) {
        Employee tec = null;
        try {
            String select = "SELECT * from empleado WHERE id_empleado  = ?";
            PreparedStatement st = conexion.prepareStatement(select);
            st.setInt(1, id_empleado);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int empId = rs.getInt("id_empleado");
                String dni = rs.getString("dni");
                String name = rs.getString("nombre");
                String email = rs.getString("correo_electronico");
                String direction = rs.getString("direccion");
                LocalTime startHour = LocalTime.parse(rs.getString("horario_entrada"));
                LocalTime exitTime = LocalTime.parse(rs.getString("horario_salida"));
                int supervisorId = rs.getInt("supervisor");
                if(type.equalsIgnoreCase("técnico")){
                    tec = new Technician(empId,dni,name,email,direction,supervisorId,startHour,exitTime);
                }else if(type.equalsIgnoreCase("asistente")){
                    tec = new Asistant(empId,dni,name,email,direction,supervisorId,startHour,exitTime);
                }
                
            }
        } catch (SQLException ex) {
            System.getLogger(OperationsDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return tec;
    }
    public static int anhadirCliente(Client cliente) throws SQLException {
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
    
    public static void deleteProduct(int idProducto) throws SQLException{
        String sentenciaSQL = "DELETE FROM producto WHERE id_producto = ? ";
        PreparedStatement ps = conexion.prepareStatement(sentenciaSQL);
        ps.executeUpdate();
    }
    
    public static void addSale(Sale sale) throws SQLException{
        String sentenciaSQL = "INSERT INTO venta(id_cliente, id_producto, fecha_venta) VALUES (?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(sentenciaSQL);
        ps.setString(1,sale.getClient());
        ps.setInt(2, sale.getProduct());
        ps.setDate(3, sale.getDateSale());
        ps.executeUpdate();
    }
    public static List<Product> obtenerProductosCliente() throws SQLException {
        List<Product> products = new ArrayList<>();
        String select = "SELECT * from producto";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
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

    public static int addProduct(Product product) throws SQLException {
        int vId = product.getId();
        String vName = product.getName();
        int vStock = product.getStock();
        String vState = product.getState();
        Double vPrice = product.getPrice();
        String vImg = product.getImg();
        int vCategory = product.getCategory();
        String sentenciaSQL = "INSERT into Product  (nombre_producto, stock, estado, price, img, category) values (?,?,?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(sentenciaSQL);
        int resultado = ps.executeUpdate();
        ps.close();
        return resultado;
    }

    public static List<Product> obtenerProductosTecnico() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sentenceSQL = "SELECT * from producto WHERE estado != 'disponible'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sentenceSQL);
        while (rs.next()) {
            Product p = new Product(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getInt("stock"),
                    rs.getString("estado"),
                    rs.getDouble("precio"),
                    rs.getInt("id_categoria")
            );
            setImageProducts(p, products);
        }
        st.close();
        rs.close();
        return products;
    }

    public static int actualizarEstadoProducto(int idProducto, String nuevoEstado) throws SQLException {
        String sentenceSQL = "UPDATE producto SET estado = ? WHERE id_producto = ?";
        PreparedStatement ps = conexion.prepareStatement(sentenceSQL);
        ps.setString(1, nuevoEstado);
        ps.setInt(2, idProducto);
        int resultado = ps.executeUpdate();
        ps.close();
        return resultado;
    }

    public static void restarStock(int idProducto, int stock) throws SQLException {
        String sentenceSQL = "UPDATE producto SET stock = stock - ? WHERE id_producto = ?";
        PreparedStatement ps = conexion.prepareStatement(sentenceSQL);
        ps.setInt(1, stock);
        ps.setInt(2, idProducto);
        ps.executeUpdate();
        ps.close();
    }
    public static void sumarStock(int idProducto, int stock) throws SQLException {
        String sentenceSQL = "UPDATE producto SET stock = stock + ? WHERE id_producto = ?";
        PreparedStatement ps = conexion.prepareStatement(sentenceSQL);
        ps.setInt(1, stock);
        ps.setInt(2, idProducto);
        ps.executeUpdate();
        ps.close();
    }
    public static void restarSaldo(String dni, double totalPrecio) throws SQLException {
        String sentenceSQL = "UPDATE cliente SET saldo = saldo - ? WHERE dni = ?";
        PreparedStatement ps = conexion.prepareStatement(sentenceSQL);
        ps.setDouble(1, totalPrecio);
        ps.setString(2, dni);
        ps.executeUpdate();
        ps.close();
    }
    public static List<Supplier> obtenerProveedores(){
        List<Supplier> suppliers = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM proveedor";
        Statement stmnt;
        try {
            stmnt = conexion.createStatement();
            ResultSet rs = stmnt.executeQuery(sentenciaSQL);
            while(rs.next()){
                Supplier sp = new Supplier(rs.getInt(1), rs.getString(2));
                suppliers.add(sp);
                
            }
        } catch (SQLException ex) {
            System.getLogger(OperationsDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return suppliers;
    }

    public static List<Category> obtenerCategorias() {
        List<Category> categories = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM categoria";
        Statement stmnt;
        try {
            stmnt = conexion.createStatement();
            ResultSet rs = stmnt.executeQuery(sentenciaSQL);
            while(rs.next()){
                Category c = new Category(rs.getInt(1), rs.getString(2));
                categories.add(c);
            }
            rs.close();
        } catch (SQLException ex) {
            System.getLogger(OperationsDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return categories;
    }
    public static void addProvee (int id_producto, int id_proveedor, int stock){
        String setenciaSQL ="INSERT INTRO provee(id_producto, id_proveedor,stock) VALUES (?,?,?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(setenciaSQL);
            ps.setInt(1, id_producto);
            ps.setInt(2, id_proveedor);
            ps.setInt(3, stock);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.getLogger(OperationsDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
