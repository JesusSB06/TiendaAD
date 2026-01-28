package controller.tecnical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
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
    private List<Product> productos;

    public TecnicalController(TecnicalJDialog view, TiendaInf model) {
        this.model = model;
        this.view = view;
        this.view.addFixButtonActionListener(this.getFixButtonActionListener());
        this.view.addCancelButtonActionListener(this.getCancelButtonActionListener());
        this.view.enableFixButton(true);
        loadProducts();
    }

    private void loadProducts() {
        try {
            productos = OperationsDB.obtenerProductosTecnico();
            view.clearTable(view.getProductsTable());
            view.addTableRenderer(view.getProductsTable());

            for (Product p : productos) {
                Vector row = new Vector();
                row.add(new javax.swing.ImageIcon(getClass().getResource(p.getImg())));
                row.add(p.getName());
                row.add(p.getPrice());
                row.add(p.getStock());
                row.add(p.getState());
                view.addRowTable(row, view.getProductsTable());
            }

            if (productos.isEmpty()) {
                JOptionPane.showMessageDialog(view, "No hay productos para reparar");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al cargar productos: " + ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
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
                Product producto = productos.get(selectedRow);
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
}
