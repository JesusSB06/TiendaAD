package model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class Sale {
    private int id;
    private LocalDate dateSale;
    private Client client;
    private List<Product> products;

    public Sale() {
    }

    public Sale(int id, Client client, List<Product> products) {
        this.id = id;
        this.dateSale = null;
        this.client = client;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateSale() {
        return dateSale;
    }

    public void setDateSale(LocalDate dateSale) {
        this.dateSale = dateSale;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
