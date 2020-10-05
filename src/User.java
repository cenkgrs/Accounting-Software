import java.sql.*;

public class User {

    private String username;
    private String password;
    private String firstname;
    private String lastname;


    public User(){}

    public User(String username, String password, String firstname, String lastname){
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
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

            user = new User(username, password, firstname, lastname);

            System.out.println("Kayıt eklendi : "+ result);

        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }finally {
            //conn.close();
            return user;
        }
    }

    public User checkUser(String username, String password) throws SQLException{
        DbHelper helper = new DbHelper();
        Connection conn;
        PreparedStatement statement;
        ResultSet resultSet;
        User user;

        String sql = "select * from users where username = ? and password = ? ";

        try {
            conn = helper.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                user = new User(resultSet.getString("username"), resultSet.getString("password"),
                        resultSet.getString("firstname"), resultSet.getString("lastname"));

                return user;
            }

        } catch (SQLException exception){
            helper.showErrorMessage(exception);
        }

        return null;

    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getFirstname() { return this.firstname; }
    public String getLastname() { return this.lastname; }

}
