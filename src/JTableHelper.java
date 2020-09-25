import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JTableHelper {
    JTable priceTable = new JTable();

    public JTable createTable(ResultSet resultSet, String[] columns){
        try {
            resultSet.last();
            int rows = resultSet.getRow();
            resultSet.beforeFirst();

            String[][] prices = new String[rows][columns.length];

            for (int i1 = 0; i1 < rows; i1++) {
                resultSet.next();
                for (int j1 = 0; j1 < columns.length; j1++) {
                    prices[i1][j1] = resultSet.getString(j1 + 1);
                }
            }

            return new JTable(prices, columns);

        }catch (SQLException e){

        }
        return priceTable;
    }
}
