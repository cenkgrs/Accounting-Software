import netscape.javascript.JSObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductsFrame extends JFrame{

    String[] columnNames = { "ID", "Code", "Name", "Price_id", "Category_id", "Firm_id"};
    String[] columns = {"Name", "Code", "Price_id", "Category_id", "Firm_id"};

    private JPanel panel = new JPanel();
    private JPanel headerPanel = new JPanel();
    private JPanel tablePanel = new JPanel();

    private JTable productTable;
    private JLabel productHeader = new JLabel("Products");
    private JScrollPane jScrollPane;
    private DefaultTableModel model;

    //Edit Panel
    private JPanel editPanel = new JPanel();
    private JTextField editIdField = new JTextField("Product Id");
    private JTextField editCodeField = new JTextField("Product Code");
    private JTextField editNameField = new JTextField("Product Name");
    private JTextField editPriceField = new JTextField("Product Price");
    private JTextField editCategoryField = new JTextField("Product Category");
    private JTextField editFirmField = new JTextField("Product Firm");

    private JButton editButton = new JButton("Edit Product");

    private JButton deleteButton = new JButton("Delete Product");

    //Add Panel
    private JPanel addPanel = new JPanel();
    private JTextField addCodeField = new JTextField("Product Code");
    private JTextField addNameField = new JTextField("Product Name");
    private JTextField addPriceField = new JTextField("Product Price");
    private JTextField addCategoryField = new JTextField("Product Category");
    private JTextField addFirmField = new JTextField("Product Firm");

    private JButton insertButton = new JButton("Insert Product");

    public ProductsFrame(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar(this));

        // Cenk: This will fill the JTable with Products
        productTable = getProducts();
        jScrollPane = new JScrollPane(productTable);

        model = (DefaultTableModel) productTable.getModel();

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        initListeners();
        setFrameSettings();

    }

    public void setLayoutManager() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1 ,2));
        editPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1 ,2));
        tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1 ,2));
    }

    public void setLocationAndSize(){

        /*deleteButton.setBounds(60,20,80,20);
        jScrollPane.setBounds(60,200,80,20);*/
    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        headerPanel.add(productHeader);

        // Inıt placeholders for edit and insert inputs
        createPlaceholder(editCodeField, "Product Code");
        createPlaceholder(editNameField, "Product Name");
        createPlaceholder(editPriceField, "Product Price");
        createPlaceholder(editCategoryField, "Product Category");
        createPlaceholder(editFirmField, "Product Firm");

        createPlaceholder(addCodeField, "Product Code");
        createPlaceholder(addNameField, "Product Name");
        createPlaceholder(addPriceField, "Product Price");
        createPlaceholder(addCategoryField, "Product Category");
        createPlaceholder(addFirmField, "Product Firm");

        // Edit Panel
        editPanel.add(editIdField);
        editIdField.setVisible(false);
        editPanel.add(editCodeField);
        editPanel.add(editNameField);
        editPanel.add(editPriceField);
        editPanel.add(editCategoryField);
        editPanel.add(editFirmField);

        editPanel.add(editButton);
        editPanel.add(deleteButton);

        // Insert Panel
        addPanel.add(addCodeField);
        addPanel.add(addNameField);
        addPanel.add(addPriceField);
        addPanel.add(addCategoryField);
        addPanel.add(addFirmField);
        addPanel.add(insertButton);

        tablePanel.add(jScrollPane);

        //Add sub panel to mail panel
        panel.add(headerPanel);
        panel.add(editPanel);
        panel.add(addPanel);
        panel.add(tablePanel);

        this.add(panel);
        //this.pack();
    }

    public void setFrameSettings(){
        setTitle("Products List");
        setBounds(10,10,1250,750);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    public JTable getProducts(){
        Connection conn = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet = null;


        try{
            conn = dbHelper.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id, code, name, price, category_id, firm_id FROM products");

            JTableHelper jTableHelper = new JTableHelper();

            return jTableHelper.createTable(resultSet, columnNames);

        }catch (SQLException exception){
            dbHelper.showErrorMessage(exception);
        }
        return productTable;
    }

    public void initListeners(){
        deleteButton.addActionListener(e -> {

            System.out.println(productTable.getSelectedRowCount());
            if(productTable.getSelectedRowCount() != 0){
                System.out.println(productTable.getSelectedRowCount());
                model.removeRow(productTable.getSelectedRow());
            }else{
                if(productTable.getRowCount() == 0){
                    JOptionPane.showMessageDialog(this, "Table is empty");
                }else{
                    JOptionPane.showMessageDialog(this, "No product is selected");
                }
            }
        });

        editButton.addActionListener(e -> {
            int id = Integer.parseInt(editIdField.getText());
            String name = editNameField.getText();
            String code = editCodeField.getText();
            String price = editPriceField.getText();
            String category = editCategoryField.getText();
            String firm = editFirmField.getText();

            Product product = new Product();
            boolean status = product.editProduct(id, name, code, price, category, firm);

            // If successful refresh the table
            if(status){
                productTable.repaint();
                JOptionPane.showMessageDialog(this, "Product successfully edited");
                return;
            }

            JOptionPane.showMessageDialog(this, "There was an error while editing the product");

        });

        // Get row values to edit panel
        productTable.getSelectionModel().addListSelectionListener(e -> {
            int row = productTable.getSelectedRow();

            if(row != -1){
                editIdField.setText(productTable.getValueAt(row,0).toString());
                editCodeField.setText(productTable.getValueAt(row,1).toString());
                editNameField.setText(productTable.getValueAt(row,2).toString());
                editPriceField.setText(productTable.getValueAt(row,3).toString());
                editCategoryField.setText(productTable.getValueAt(row,4).toString());
                editFirmField.setText(productTable.getValueAt(row,5).toString());
            }
        });
    }

    // Cenk: This function creates placeholder functionality for every textField
    public void createPlaceholder(JTextField textField, String placeholder){

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textField.getText().equals(placeholder)){
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(textField.getText().isEmpty()){
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }
}
