/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.products;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import model.Category;
import model.OperationsDB;
import model.Product;
import model.TiendaInf;
import view.ProductsJDialog;

/**
 *
 * @author dam2_alu19@inf.ald
 */
public class ProductsController {

    private ProductsJDialog view;
    private TiendaInf model;

    public ProductsController(ProductsJDialog view, TiendaInf model) throws SQLException {
        this.model = model;
        this.view = view;
        this.initComponents();
    }

    private void updateTable(JTable table) throws SQLException {
        view.clearTable(table);
        List<Product> products = OperationsDB.obtenerProductos();
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
            row.add(icon);
            row.add(d.getName());
            row.add(d.getPrice());
            row.add(d.getStock());
            view.addRowTable(row, table);
        }

    }

    private void initComponents() throws SQLException {
        this.updateTable(view.getProductsTable());
    }
}
