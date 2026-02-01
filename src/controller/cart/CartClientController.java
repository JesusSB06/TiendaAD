/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.cart;

import controller.client.ClientController;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.OperationsDB;
import model.Product;
import model.Sale;
import model.TiendaInf;
import view.CartClientJDialog;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author alumno
 */
public class CartClientController {
    private CartClientJDialog view;
    private TiendaInf model;
    private ClientController parent;

    public CartClientController(CartClientJDialog view, TiendaInf model,ClientController parent) {
        this.view = view;
        this.model = model;
        this.parent = parent;
        this.initComponents();
        this.view.setBuyButtonActionListener(this.setBuyButtonActionListener());
        this.view.setCancelButtonListener(this.setCancelButtonActionListener());
        this.view.setDeleteButtonActionListener(this.setDeleteButtonActionListener());
    }
    
    private void initComponents(){
        this.updateTable(view.getCartTable());
        this.updateLabels();
    }
    private void updateLabels() {
        this.view.setTotalPriceLabel("Precio total: " + String.format("%.2f", getTotalPrice()));
        this.view.setCreditLabel(String.valueOf("Saldo actual: " + model.getClient().getSaldo()));
    }

    private void updateTable(JTable table){
        view.clearTable(table);
        for(Map.Entry<Product,Integer> p : model.getCart().entrySet()){
            Vector row = new Vector();
            ImageIcon icon = null;
            try {
                URL imageUrl = getClass().getResource(p.getKey().getImg());
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
            row.add(p.getKey().getId());
            row.add(icon);
            row.add(p.getKey().getName());
            row.add(p.getKey().getPrice());
            row.add(p.getValue());
            view.addRowTable(row, table);
        }
    }
    private double getTotalPrice(){
        double totalPrice = 0.0;
        for(Map.Entry<Product,Integer> p : model.getCart().entrySet()){
            double price = p.getKey().getPrice() * p.getValue();
            totalPrice += price;
        }
        return totalPrice;
    }
    private ActionListener setBuyButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getClient().getSaldo() > getTotalPrice()){
                    for(Map.Entry<Product,Integer> p : model.getCart().entrySet()){
                        try {
                            Product pd = new Product(p.getKey().getId(),p.getKey().getName(), p.getValue(), "vendido", p.getKey().getPrice(),p.getKey().getCategory());
                            Sale sale = new Sale(model.getClient().getDni(),pd.getId(), Date.valueOf(LocalDate.now()));
                            OperationsDB.addProduct(pd);
                            OperationsDB.addSale(sale);       
                            model.getCart().remove(p.getKey());
                        } catch (SQLException ex) {
                            System.getLogger(CartClientController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }
                    }
                    try {
                        OperationsDB.restarSaldo(model.getClient().getDni(), getTotalPrice());
                    } catch (SQLException ex) {
                        System.getLogger(CartClientController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                    JOptionPane.showMessageDialog(view, "Se ha realizado la compra sin problemas");
                    parent.updateTable(model.getProducts());
                    view.dispose();
                }else{
                    JOptionPane.showMessageDialog(view, "Error: saldo insuficiente","Error", JOptionPane.ERROR_MESSAGE);
                }
                updateLabels();
            }
        };
        return al;
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
    private ActionListener setDeleteButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getCartTable().getSelectedRow();
                if(row != -1){
                    try {
                        int id = (int) view.getCartTable().getModel().getValueAt(row, 0);
                        int stock = (int) view.getCartTable().getModel().getValueAt(row, 4);
                        try {
                            OperationsDB.sumarStock(id,stock );
                        } catch (SQLException ex) {
                            System.getLogger(CartClientController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }
                        model.deleteToCart(model.getCartProduct(id));
                        view.clearRow(view.getCartTable());
                        model.setProducts(OperationsDB.obtenerProductosCliente());
                        parent.updateTable(model.getProducts());
                    } catch (SQLException ex) {
                        System.getLogger(CartClientController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(view, "Error: seleccione un producto","Error", JOptionPane.ERROR_MESSAGE);
                }
                updateLabels();
            }
        };
        return al;
    }
}
