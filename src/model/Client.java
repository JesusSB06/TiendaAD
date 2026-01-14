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
    private int telephone;
    private List<Sale> ventas;

    public Client() {
    }

    public Client(String dni, String name, String correoElectronico, String password, int telephone, List<Sale> ventas) {
        this.dni = dni;
        this.name = name;
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.telephone = telephone;
        this.ventas = ventas;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public List<Sale> getVentas() {
        return ventas;
    }

    public void setVentas(List<Sale> ventas) {
        this.ventas = ventas;
    }
}
