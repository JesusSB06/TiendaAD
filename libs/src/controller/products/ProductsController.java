/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.products;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Category;
import model.Client;
import model.Product;
import model.Sale;
import view.ProductsJDialog;

/**
 *
 * @author dam2_alu19@inf.ald
 */
public class ProductsController {
    private Client user;
    private Sale sale;
    private ProductsJDialog view;

    public ProductsController( ProductsJDialog view) {
        this.user = null;
        this.sale = null;
        this.view = view;
        this.initComponents();
        this.view.setAddButtonListener(this.setAddButtonActionListener());
        this.view.setCancelButtonListener(this.setCancelButtonActionListener());
    }



    private void initComponents() {
        this.updateTable(view.getProductsTable());
    }    
    
    private void updateTable(JTable table) {
        view.clearTable(table);
        Product d = new Product(123, "Motomami", 45, "deplorable", 56.89, "/imagenes/motomami.jpeg", new Category(3434, "·WWWWWW"));
            Vector row = new Vector();
            ImageIcon icon = null;
            try {
                URL imageUrl = getClass().getResource(d.getImg());
                if (imageUrl != null) {
                    icon = new ImageIcon(ImageIO.read(imageUrl));
                } else {
                    System.out.println("No se encontró la imagen");
                }
            } catch (IOException e) {
                e.printStackTrace();
}
            row.add(icon);
            row.add(d.getName());
            row.add(d.getPrice());
            row.add(d.getStock());
            view.addRowTable(row, table);
        
    }
    
    private ActionListener  setAddButtonActionListener(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getSelectedRow();
                if(row != 1){
                    int cant = Integer.parseInt(JOptionPane.showInputDialog(view, "Introduce la cantidad a comprar"));
                    int stock = (int) view.getProductsTable().getModel().getValueAt(row, 2);
                    if(stock >= cant){
                        //TODO
                    }else{
                        JOptionPane.showMessageDialog(view, "Error: excede la cantidad disponible");
                    }
                }else{
                    JOptionPane.showMessageDialog(view, "Error: no se ha seleccionado ninguna fila");
                }
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

}
