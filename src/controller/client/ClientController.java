/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.client;

import controller.cart.CartClientController;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.OperationsDB;

import model.Product;
import model.TiendaInf;
import view.CartClientJDialog;
import view.ClientsJDialog;

/**
 *
 * @author dam2_alu19@inf.ald
 */
public class ClientController {

    private ClientsJDialog view;
    private TiendaInf model;

    public ClientController(ClientsJDialog view, TiendaInf model) {
        this.model = model;
        this.view = view;
        this.initComponents();
        this.view.setSearchTextFieldListener(this.setSearchTextFieldDocumentListener());
        this.view.setCancelButtonListener(this.setCancelButtonActionListener());
        this.view.setAddButtonListener(this.setAddButtonActionListener());
        this.view.setGoToCartButtonListener(this.setGoToCartActionListener());
    }

    private void initComponents() {
        try {
            model.setProducts(OperationsDB.obtenerProductosCliente());
        } catch (SQLException ex) {
            System.getLogger(ClientController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        this.updateTable( model.getProducts());
    }
    public void updateTable(List<Product> products) {
        view.clearTable(view.getProductsTable());
        for (Product d : products) {
            
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

    }
    
    private DocumentListener setSearchTextFieldDocumentListener(){
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
        List<Product> toRemove = new ArrayList<>();
        List<Product> finalProducts = new ArrayList<>();
        String filter = view.getSearchTextField();
        if (filter.isEmpty()) {
            updateTable( model.getProducts());
        } else {
            for (Product p : model.getProducts()) {
                if (p.getName().toLowerCase().contains(filter.toLowerCase())) {
                    finalProducts.add(p);
                }
                if (p.getStock() <= 0) {
                    toRemove.add(p);
                    try {
                        OperationsDB.deleteProduct(p.getId());
                    } catch (SQLException ex) {
                        System.getLogger(ClientController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }
            }
            for (Product p : toRemove) {
                model.getProducts().remove(p);
            }
            updateTable( finalProducts);
        }

    }
    
    private ActionListener setCancelButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        };
        return al;
    }
    private ActionListener setAddButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getProductsTable().getSelectedRow();
                if(row != -1){
                    String stockString = JOptionPane.showInputDialog(view, "Introduzca la cantidad deseada del producto").trim();
                    int id = (int) view.getProductsTable().getModel().getValueAt(row, 0);
                    try{
                        int stock = Integer.parseInt(stockString);
                        OperationsDB.restarStock(id , stock);
                        model.addToCart(model.getProduct(id), stock);
                        model.setProducts(OperationsDB.obtenerProductosCliente());
                        updateTable(model.getProducts());
                    }catch(NumberFormatException nfe){
                        JOptionPane.showMessageDialog(view,"Error: el valor debe ser unicamente un número", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        System.getLogger(ClientController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(view, "Error: seleccione un producto","Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        };
        return al;
                
    }
    
    private ActionListener setGoToCartActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getCart().isEmpty()){
                    JOptionPane.showMessageDialog(view, "Error: el carro esta vacío", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    CartClientJDialog ccd = new CartClientJDialog(view, true);
                    CartClientController ccc = new CartClientController(ccd, model, ClientController.this);
                    ccd.setVisible(true);
                }
            }
        };
        return al;
    }
}
