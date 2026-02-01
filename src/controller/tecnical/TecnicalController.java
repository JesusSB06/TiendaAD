package controller.tecnical;

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
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.OperationsDB;
import model.Product;
import model.TiendaInf;
import view.TecnicalJDialog;

/**
 *
 * @author dam2_alu03@inf.ald
 */
public class TecnicalController {

    private TecnicalJDialog view;
    private TiendaInf model;

    public TecnicalController(TecnicalJDialog view, TiendaInf model) {
        this.view = view;
        this.model = model;
        try {
            model.setProducts(OperationsDB.obtenerProductosTecnico());
        } catch (SQLException ex) {
            System.getLogger(TecnicalController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        loadProducts(model.getProducts());

        this.view.addFixButtonActionListener(this.getFixButtonActionListener());
        this.view.setSearchTextFieldListener(this.setSearchTextFieldDocumentListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonActionListener());
        this.view.enableFixButton(true);

    }

    private void loadProducts(List<Product> products) {
        List<Product> toRemove = new ArrayList<Product>();
        view.clearTable(view.getProductsTable());
        for (Product p : products) {
            if (p.getStock() <= 0) {
                toRemove.add(p);
                try {
                    OperationsDB.deleteProduct(p.getId());
                } catch (SQLException ex) {
                    System.getLogger(TecnicalController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }

            Vector row = new Vector();
            ImageIcon icon = null;
            try {
                URL imageUrl = getClass().getResource(p.getImg());
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
            row.add(p.getName());
            row.add(p.getPrice());
            row.add(p.getStock());
            row.add(p.getState());
            view.addRowTable(row, view.getProductsTable());
        }
        for (Product p : toRemove) {
            model.getProducts().remove(p);
        }
        if (model.getProducts().isEmpty()) {
            JOptionPane.showMessageDialog(view, "No hay productos para reparar");
        }
    }

    public ActionListener getFixButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedRow = view.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(view,"Por favor, seleccione un producto de la tabla","Aviso",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Product producto = model.getProducts().get(selectedRow);
                if (producto.getState().equals("disponible")) {
                    JOptionPane.showMessageDialog(view,"Este producto ya está reparado","Aviso",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int confirmacion = JOptionPane.showConfirmDialog(view,"¿Está seguro de que desea marcar como reparado el producto: " + producto.getName() + "?","Confirmar reparación",JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        int resultado = OperationsDB.actualizarEstadoProducto(producto.getId(), "disponible");
                        if (resultado > 0) {
                            view.setState(selectedRow, "disponible");
                            producto.setState("disponible");
                            JOptionPane.showMessageDialog(view,"Producto reparado con éxito","Completado",JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(view,"Error al reparar producto: " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        return al;
    }

    public ActionListener getCancelButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.dispose();
            }
        };
        return al;
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
        
        List<Product> finalProducts = new ArrayList<Product>();
        String filter = view.getSearchTextField();
        if (filter.isEmpty()) {
            loadProducts(model.getProducts());
        } else {
            for (Product p : model.getProducts()) {
                if (p.getName().toLowerCase().contains(filter.toLowerCase())) {
                    finalProducts.add(p);
                }
  
            }

            loadProducts(finalProducts);
        }

    }
}
