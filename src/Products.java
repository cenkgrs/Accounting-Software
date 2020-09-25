import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Products extends JFrame{

    private JPanel panel1 = new JPanel();
    private JTable priceTable;
    private JLabel priceHeader = new JLabel();
    private JScrollPane jScrollPane;

    public Products(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar());

        priceTable = getProducts();
        jScrollPane = new JScrollPane(priceTable);

        addComponentsToContainer();
        setFrameSettings();

    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        this.add(priceHeader);
        this.add(jScrollPane);
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

        String[] columnNames = { "ID", "Name", "Price_id", "Category_id", "Firm_id" };

        try{
            conn = dbHelper.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id, name, price, category_id, firm_id FROM products");

            JTableHelper jTableHelper = new JTableHelper();

            return jTableHelper.createTable(resultSet, columnNames);

        }catch (Exception e){

        }
        return priceTable;
    }
}
