/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dam2_alu19@inf.ald
 */
public class TiendaInf {
    private List<Product> products;
    private List<Supplier> suppliers;
    private List<Category> categories;
    private Client client;
    private Employee employee;

    public TiendaInf() {
        this.products = new ArrayList<Product>();
        this.suppliers = new ArrayList<Supplier>();
        this.categories = new ArrayList<Category>();
        this.client = null;
        this.employee = null;
    }
    
    public void addToCart (Product product, int stock ){
        client.getCart().put( product, stock);
    }
    public void deleteToCart(Product product){
        client.getCart().remove(product);
    }
    
    public Product getCartProduct(int id){
        Product p = null;
        for(Map.Entry<Product,Integer> mp : client.getCart().entrySet()){
            if(mp.getKey().getId() == id){
                p = mp.getKey();
            }
        }
        return p;
    }
    public HashMap<Product,Integer> getCart (){
        return client.getCart();
    }
    
    public Product getProduct(int id){
        Product p = null;
        for(Product pd: products){
            if(pd.getId() == id){
                p = pd;
            }
        }
        return p;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    
}
