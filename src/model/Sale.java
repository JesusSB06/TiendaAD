package model;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;
/**
 *
 * @author dam2_alu03@inf.ald
 */
public class Sale {
    private int id;
    private Date dateSale;
    private String client;
    private int product;

    public Sale() {
    }

    public Sale(int id, Date dateSale, String client,int product) {
        this.id = id;      
        this.client = client;
        this.product = product;
        this.dateSale = dateSale;
    }
    public Sale(String client,int product, Date dateSale) {     
        this.client = client;
        this.product = product;
        this.dateSale = dateSale;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateSale() {
        return dateSale;
    }

    public void setDateSale(Date dateSale) {
        this.dateSale = dateSale;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

}
