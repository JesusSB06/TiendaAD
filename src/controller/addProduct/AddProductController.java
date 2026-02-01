/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.addProduct;

import controller.asistant.AsistantController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import model.Category;
import model.OperationsDB;
import model.Product;
import model.Supplier;
import model.TiendaInf;
import view.AddProductJDialog;
import view.AsistantJDialog;

/**
 *
 * @author alumno
 */
public class AddProductController {
    private AddProductJDialog view;
    private TiendaInf model;
    private AsistantController parent;
   

    public AddProductController(AddProductJDialog view, TiendaInf model, AsistantController parent) {
        this.view = view;
        this.model = model;
        this.parent = parent;
        this.initComponents();
    }
    
    
    private void initComponents(){
        setComBoxSupplier();
        setComboBoxProduct();
        setComboBoxCategory();
    }
    
    private void setComBoxSupplier(){
        model.setSuppliers(OperationsDB.obtenerProveedores());
        for(Supplier sp : model.getSuppliers()){
            view.addSelectionComboBoxSupplier(sp.getId() + ". " + sp.getCompanyName());
        }
    }
    private void setComboBoxProduct(){
        for(Product p : model.getProducts()){
            view.addSelectionComboBoxProducts(p.getId() + ". " + p.getName());
        }
    }
    private void setComboBoxCategory(){
        model.setCategories(OperationsDB.obtenerCategorias());
        for(Category c : model.getCategories()){
            view.addSelectionComboBoxCategory(c.getId() + ". " + c.getName());
        }
    }

    private ActionListener setAddExistentProductActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String infProduct[] = view.getProduct().split(".");
                    String infSupplier[] = view.getSupplierExistentProduct().split(".");
                    OperationsDB.sumarStock(Integer.parseInt(infProduct[0]), view.getStockExistentProduct());
                    OperationsDB.addProvee(Integer.parseInt(infProduct[0]), Integer.parseInt(infSupplier[0]), view.getStockExistentProduct());
                    parent.updateTable(model.getProducts());
                    view.dispose();
                } catch (SQLException ex) {
                    System.getLogger(AddProductController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }

        };
        return al;
    }

    private ActionListener setAddNewProductActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isFormValid()){
                    String infCategory[] = view.getCategory().split("."); 
                    Product p = new Product(view.getNameProduct(), view.getStockNewProduct(), "nuevo", view.getPriceNewProduct(),Integer.parseInt(infCategory[0]));
                    try {
                        OperationsDB.addProduct(p);
                        parent.updateTable(model.getProducts());
                        view.dispose();
                    } catch (SQLException ex) {
                        System.getLogger(AddProductController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
            }
        };
        return al;
    }
    private boolean isFormValid() {
        if (view.getStockNewProduct() == 0) {
            return false;
        }
        if (view.getPriceNewProduct() == 0.0) {
            return false;
        }
        if (view.getNameProduct().isEmpty()) {
            return false;
        }
        if (view.getSupplierNewObject().isEmpty()) {
            return false;
        }
        if (view.getProduct().isEmpty()) {
            return false;
        }
        if (view.getCategory().isEmpty()) {
            return false;
        }

        return true;
    }

}
