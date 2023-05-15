package org.example.presentation;

import org.example.controller.ClientController;
import org.example.controller.OrderController;
import org.example.controller.ProductController;
import org.example.dao.DaoRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableUtil {
    private ClientController clientController;
    private OrderController orderController;
    private ProductController productController;
    private JFrame frame;
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public static <Type> DefaultTableModel createTable(DaoRepository daoRepository, Class<Type> classInstance) throws SQLException {
        DefaultTableModel tableModel = new DefaultTableModel();
        Field[] fields = classInstance.getDeclaredFields();
        ArrayList<Type> listOfData = daoRepository.findAll();

        for(Field field: fields){
            tableModel.addColumn(field.getName());
        }
        for (Type item : listOfData) {
            Object[] rowData = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    rowData[i] = fields[i].get(item);
                } catch (IllegalAccessException e) {
                    rowData[i] = null;
                }
            }
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
}
