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

        getPrices();

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

    public void getPrices(){
        Connection conn = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet = null;

        String[] columnNames = { "Name", "Price", "Category_id", "Firm_id" };

        try{
            conn = dbHelper.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT name, price, category_id, firm_id FROM products");

            resultSet.last();
            int rows = resultSet.getRow();
            resultSet.beforeFirst();

            String[][] prices = new String[rows][columnNames.length];

            for (int i1 = 0; i1 < rows; i1++) {
                resultSet.next();
                for (int j1 = 0; j1 < columnNames.length; j1++) {
                    prices[i1][j1] = resultSet.getString(j1 + 1);
                }
            }

            priceTable = new JTable(prices, columnNames);


        }catch (Exception e){

        }

    }
}

