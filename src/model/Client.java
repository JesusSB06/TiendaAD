package model;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class Client {

    private String dni;
    private String nombre_cliente;
    private String correo_electronico;
    private String contrasenha;
    private double saldo;
    private String telefono;
    private HashMap<Product, Integer> cart;

    public Client(String dni, String nombre_cliente, String correo_electronico,  String telefono,double saldo, String contrasenha) {
        this.dni = dni;
        this.nombre_cliente = nombre_cliente;
        this.correo_electronico = correo_electronico;
        this.contrasenha = contrasenha;
        this.saldo = saldo;
        this.telefono = telefono;
        this.cart = new HashMap<Product, Integer>();
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<Product, Integer> cart) {
        this.cart = cart;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    


}
