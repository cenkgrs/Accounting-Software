import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Prices extends JFrame {
    private JPanel panel1;
    private JTable priceTable;

    public Prices(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar());

        priceTable = getPrices();

        addComponentsToContainer();

        setFrameSettings();

    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        this.add(priceTable);
    }


    public void setFrameSettings(){
        setTitle("Prices List");
        setBounds(10,10,1250,750);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    public JTable getPrices(){
        Connection conn = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet = null;

        String[] columnNames = { "Name", "Price", "Category_id", "Firm_id" };

        try{
            conn = dbHelper.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT name, price, category_id, firm_id FROM products");

            JTableHelper jTableHelper = new JTableHelper();

            return jTableHelper.createTable(resultSet, columnNames);

        }catch (Exception e){

        }
        return priceTable;
    }
}

