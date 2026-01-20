package model;

import java.util.List;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class Client {
    private String dni;
    private String name;
    private String correoElectronico;
    private String password;
    private String telephone;
    private List<Sale> ventas;

    public Client() {
    }

    public Client(String dni, String name, String correoElectronico, String password, String telephone, List<Sale> ventas) {
        this.dni = dni;
        this.nombre_cliente = "";
        this.correo_electronico = correoElectronico;
        this.contrasenha = "";
        this.telefono = telephone;
        this.ventas = ventas;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return nombre_cliente;
    }

    public void setName(String name) {
        this.nombre_cliente = name;
    }

    public String getCorreoElectronico() {
        return correo_electronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correo_electronico = correoElectronico;
    }

    public String getPassword() {
        return contrasenha;
    }

    public void setPassword(String password) {
        this.contrasenha = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Sale> getVentas() {
        return ventas;
    }

    public void setVentas(List<Sale> ventas) {
        this.ventas = ventas;
    }


}
