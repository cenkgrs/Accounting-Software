import java.sql.*;

public class Product {
    private String name;
    private String code;
    private String price;
    private String category_id;
    private String firm_id;

    public Product(){}
    public Product(String name, String code, String price, String category_id, String firm_id) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.category_id = category_id;
        this.firm_id = firm_id;
    }

    public boolean editProduct(int id, String name, String code, String price, String category_id, String firm_id){
        Connection conn = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement st = null;
        ResultSet resultSet = null;

        try{
            conn = dbHelper.getConnection();
            String sql = "UPDATE products SET code = ?, name = ?, price = ?, category_id = ?, firm_id = ? WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, code);
            st.setString(2, name);
            st.setString(3, price);
            st.setString(4, category_id);
            st.setString(5, firm_id);
            st.setInt(6, id);
            st.executeUpdate();

            return true;

        }catch (SQLException exception){
            dbHelper.showErrorMessage(exception);

            return false;
        }

    }
}
