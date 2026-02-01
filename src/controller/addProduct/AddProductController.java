/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.addProduct;

import model.OperationsDB;
import model.Product;
import model.Supplier;
import model.TiendaInf;
import view.AddProductJDialog;

/**
 *
 * @author alumno
 */
public class AddProductController {
    private AddProductJDialog view;
    private TiendaInf model;

    public AddProductController(AddProductJDialog view, TiendaInf model) {
        this.view = view;
        this.model = model;
        this.initComponents();
    }
    
    
    private void initComponents(){
        setComBoxSupplier();
        setComboBoxProduct();
    }
    
    private void setComBoxSupplier(){
        model.setSuppliers(OperationsDB.ObtenerProveedores());
        for(Supplier sp : model.getSuppliers()){
            view.addSelectionComboBoxSupplier(sp.getId() + ". " + sp.getCompanyName());
        }
    }
    private void setComboBoxProduct(){
        for(Product p : model.getProducts()){
            view.addSelectionComboBoxProducts(p.getId() + ". " + p.getName());
        }
    }

    
}
