/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.asistant;

import controller.addProduct.AddProductController;
import controller.client.ClientController;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.OperationsDB;
import model.Product;
import model.TiendaInf;
import view.AddProductJDialog;
import view.AsistantJDialog;

/**
 *
 * @author alumno
 */
public class AsistantController {

    private AsistantJDialog view;
    private TiendaInf model;

    public AsistantController(AsistantJDialog view, TiendaInf model) {
        this.view = view;
        this.model = model;
        this.initComponents();
        this.view.addCancelButtonActionListener(this.setCancelButtonActionListener());
        this.view.setSearchTextFieldListener(this.setSearchTextFieldDocumentListener());
        this.view.setAddButtonListener(this.setAddProductButtonActionListener());
    }

    private void initComponents() {
        try {
            model.setProducts(OperationsDB.obtenerProductos());
        } catch (SQLException ex) {
            System.getLogger(ClientController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        this.updateTable(model.getProducts());
    }

    public void updateTable(List<Product> products) {
        List<Product> toRemove = new ArrayList<Product>();
        view.clearTable(view.getProductsTable());
        for (Product d : products) {
            if (d.getStock() <= 0) {
                toRemove.add(d);
                try {
                    OperationsDB.deleteProduct(d.getId());
                } catch (SQLException ex) {
                    System.getLogger(AsistantController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
            Vector row = new Vector();
            ImageIcon icon = null;
            try {
                URL imageUrl = getClass().getResource(d.getImg());
                if (imageUrl != null) {
                    BufferedImage img = ImageIO.read(imageUrl);
                    Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(scaledImg);
                } else {
                    System.out.println("No se encontró la imagen");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            row.add(d.getId());
            row.add(icon);
            row.add(d.getName());
            row.add(d.getPrice());
            row.add(d.getStock());
            view.addRowTable(row, view.getProductsTable());
        }
        for (Product p : toRemove) {
            model.getProducts().remove(p);
        }
    }

    private DocumentListener setSearchTextFieldDocumentListener() {
        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SearchProduct();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SearchProduct();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SearchProduct();
            }
        };
        return dl;
    }

    private void SearchProduct() {
        
        List<Product> finalProducts = new ArrayList<Product>();
        String filter = view.getSearchTextField();
        if (filter.isEmpty()) {
            updateTable( model.getProducts());
        } else {
            for (Product p : model.getProducts()) {
                if (p.getName().toLowerCase().contains(filter.toLowerCase())) {
                    finalProducts.add(p);
                }

            }

            updateTable( finalProducts);
        }

    }

    private ActionListener setCancelButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        };
        return al;
    }
    
    private ActionListener setAddProductButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductJDialog apd = new AddProductJDialog(view,true);
                AddProductController apc =  new AddProductController(apd, model, AsistantController.this);
                apd.setVisible(true);
            }
        };
        return al;
    }
}
