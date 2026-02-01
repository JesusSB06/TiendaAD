/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaceView;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author alumno
 */
public interface interfaceView {
    void applyStylesButton();
    default void addTableRenderer(JTable table, int column) {
        table.setRowHeight(80);

        table.getColumnModel().getColumn(column).setCellRenderer(new TableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);

                if (value instanceof ImageIcon) {
                    label.setIcon((ImageIcon) value);
                } else {
                    label.setText(value != null ? value.toString() : "No Image");
                }

                return label;
            }
        });

        DefaultTableCellRenderer textCentrado = new DefaultTableCellRenderer();
        textCentrado.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != column) {
                table.getColumnModel().getColumn(i).setCellRenderer(textCentrado);
            }

        }
    }
    default void ApplyStylesTable(JScrollPane scroll, JTable table) {
        scroll.getViewport().setBackground(Color.WHITE);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setShowGrid(false);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setBorder(null);
        table.setSelectionBackground(Color.BLACK);
        table.setSelectionForeground(Color.WHITE);
        JScrollBar vertical = scroll.getVerticalScrollBar();
        vertical.setBackground(Color.WHITE);
        vertical.setForeground(Color.LIGHT_GRAY);

    }

    default void clearTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        table.clearSelection();
        table.revalidate();
        table.repaint();
    }
    
    default void clearRow(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        model.removeRow(row);
    }
    
    default void addRowTable(Vector row, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(row);
    }
    
    
}
