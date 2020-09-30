import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Prices extends JFrame {
    private JPanel panel1 = new JPanel();
    private JTable priceTable;
    private JLabel priceHeader;
    private JScrollPane jScrollPane;

    public Prices(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar(this));

        priceTable = getPrices();
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

        String[] columnNames = { "ID", "Price", "Currency", "Tax" };

        try{
            conn = dbHelper.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT id, price, currency, tax FROM prices");

            JTableHelper jTableHelper = new JTableHelper();

            return jTableHelper.createTable(resultSet, columnNames);

        }catch (SQLException exception){
            dbHelper.showErrorMessage(exception);
        }
        return priceTable;
    }
}

