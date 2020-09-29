import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Products extends JFrame{

    private JPanel panel1 = new JPanel();
    private JTable productTable;
    private JLabel priceHeader = new JLabel();
    private JScrollPane jScrollPane;
    private JButton deleteButton = new JButton();
    private DefaultTableModel model;

    public Products(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar());

        productTable = getProducts();
        jScrollPane = new JScrollPane(productTable);

        addComponentsToContainer();
        setLocationAndSize();
        initListeners();
        setFrameSettings();

    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        this.add(deleteButton);
        this.add(priceHeader);
        this.add(jScrollPane);
    }

    public void setLocationAndSize(){

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

            if(productTable.getSelectedRow() == 1){
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
