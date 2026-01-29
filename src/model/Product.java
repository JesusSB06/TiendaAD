package model;

import java.util.HashMap;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class Product {

    private int id;
    private String name;
    private int stock;
    private String state;
    private Double price;
    private String img;
    private int category;
    
    
    public Product(int id, String name, int stock, String state, Double price, int category) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.state = state;
        this.price = price;
        this.img = "";
        this.category = category;
    }
    public Product( String name, int stock, String state, Double price, int category) {
        this.name = name;
        this.stock = stock;
        this.state = state;
        this.price = price;
        this.img = "";
        this.category = category;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }



}
