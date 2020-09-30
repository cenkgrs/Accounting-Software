import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Products extends JFrame{

    private JPanel panel = new JPanel();
    private JPanel headerPanel = new JPanel();
    private JPanel tablePanel = new JPanel();

    private JTable productTable;
    private JLabel productHeader = new JLabel("Products");
    private JScrollPane jScrollPane;
    private DefaultTableModel model;

    //Edit Panel
    private JPanel editPanel = new JPanel();
    private JTextField codeField = new JTextField("Product Code");
    private JTextField nameField = new JTextField("Product Name");
    private JTextField priceField = new JTextField("Product Price");
    private JTextField categoryField = new JTextField("Product Category");
    private JTextField firmField = new JTextField("Product Firm");

    private JButton deleteButton = new JButton("Delete Product");


    public Products(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar(this));

        // Cenk: This will fill the JTable with Products
        productTable = getProducts();
        jScrollPane = new JScrollPane(productTable);

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        initListeners();
        setFrameSettings();

    }

    public void setLayoutManager()
    {
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

        createPlaceholder(codeField, "Product Code");
        createPlaceholder(nameField, "Product Name");
        createPlaceholder(priceField, "Product Price");
        createPlaceholder(categoryField, "Product Category");
        createPlaceholder(firmField, "Product Firm");

        editPanel.add(codeField);
        editPanel.add(nameField);
        editPanel.add(priceField);
        editPanel.add(categoryField);
        editPanel.add(firmField);
        editPanel.add(deleteButton);

        tablePanel.add(jScrollPane);

        panel.add(headerPanel);
        panel.add(editPanel);
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

        String[] columnNames = { "ID", "Name", "Code", "Price_id", "Category_id", "Firm_id"};

        try{
            conn = dbHelper.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id, name, code, price, category_id, firm_id FROM products");

            JTableHelper jTableHelper = new JTableHelper();

            return jTableHelper.createTable(resultSet, columnNames);

        }catch (SQLException exception){
            dbHelper.showErrorMessage(exception);
        }
        return productTable;
    }

    public void initListeners(){
        deleteButton.addActionListener(e -> {

            model = (DefaultTableModel) productTable.getModel();

            if(productTable.getSelectedRow() != -1){
                model.removeRow(productTable.getSelectedRow());
            }else{
                if(productTable.getRowCount() == 0){
                    JOptionPane.showMessageDialog(this, "Table is empty");
                }else{
                    JOptionPane.showMessageDialog(this, "No product is selected");
                }
            }
        });

    }

    // Cenk: This function creates placeholder functionality for every textField
    public void createPlaceholder(JTextField textField, String placeholder){
        System.out.println(placeholder);
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
