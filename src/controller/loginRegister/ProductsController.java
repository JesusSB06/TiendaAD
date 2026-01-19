/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.loginRegister;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import model.Category;
import model.Product;
import view.ProductsJDialog;

/**
 *
 * @author dam2_alu19@inf.ald
 */
public class ProductsController {

    private ProductsJDialog view;


    public ProductsController(ProductsJDialog view) {
        this.view = view;
        this.initComponents();
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

    private void initComponents() {
        this.updateTable(view.getProductsTable());
    }
}
