package model;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class Product {

    private int id;
    private String name;
    private int stock;
    private boolean state;
    private Double price;
    private String img;
    private Category category;

    public Product() {
    }
    
    public Product(int id, String name, int stock, boolean state, Double price, String img, Category category) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.state = state;
        this.price = price;
        this.img = img;
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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
