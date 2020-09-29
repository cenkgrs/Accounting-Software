import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Products extends JFrame{

    private JPanel panel = new JPanel();
    private JPanel editPanel = new JPanel();
    private JPanel tablePanel = new JPanel();

    private JTable productTable;
    private JLabel priceHeader = new JLabel();
    private JScrollPane jScrollPane;
    private JButton deleteButton = new JButton("Delete Product");
    private DefaultTableModel model;

    public Products(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar());

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
        editPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1 ,2));
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1 ,2));
    }

    public void setLocationAndSize(){
        /*deleteButton.setBounds(60,20,80,20);
        jScrollPane.setBounds(60,200,80,20);*/
    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        editPanel.add(deleteButton);
        editPanel.add(priceHeader);

        tablePanel.add(jScrollPane);

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

        String[] columnNames = { "ID", "Name", "Price_id", "Category_id", "Firm_id"};

        try{
            conn = dbHelper.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id, name, price, category_id, firm_id FROM products");

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

}
