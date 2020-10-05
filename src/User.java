import java.sql.*;

public class User {

    private String username;
    private String password;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User createUser(String username, String password, String firstname, String lastname) throws SQLException {
        Connection conn = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        User user = null;

        try {
            conn = helper.getConnection();
            String sql = "insert into users(username, password, firstname, lastname) " +
                    " values (?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstname);
            statement.setString(4, lastname);


            int result = statement.executeUpdate();

            user = new User(username, password);

            System.out.println("Kayıt eklendi : "+ result);

        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }finally {
            //conn.close();
            return user;
        }
    }

    public User checkUser(String username, String password) throws SQLException{
        Connection conn = null;
        DbHelper helper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            conn = helper.getConnection();
            statement = conn.createStatement();

            resultSet = statement.executeQuery("select name, password from users where username = ? and password = ?");

            while (resultSet.next()){
                user = new User(resultSet.getString("username"), resultSet.getString("password"));
            }

        } catch (SQLException exception){

        }
        return user;

    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
}
