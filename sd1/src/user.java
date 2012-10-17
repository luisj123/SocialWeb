/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author plucas
 */
//import java.io.Serializable;
import java.sql.*;

//class User implements Data_lists, Serializable {
class User {

    private String name;
    private Connection connection;
    private Statement statement;
    private PreparedStatement st;
    private ResultSet rs;

    public User(String _name) {
        try {
            name = _name;
            //users.add(this);
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sd.db");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
    
    

    public int loginUser(String user, String pass) {

        int ret = 0;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("select pass from users where nome='" + user + "';");
            if (rs.getString("pass").equals(pass)) {
                ret = 1;
            }
            connection.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

        return ret;
    }

    public int registerUser(String user, String pass) {

        int ret = 0;

        try {
            String INSERT_RECORD = "insert into users (nome, pass) values(?,?)";

            st = connection.prepareStatement(INSERT_RECORD);
            st.setString(1, user);
            st.setString(2, pass);

            ret = (st.executeUpdate() >= 1) ? 1 : 0;

            connection.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

        return ret;

    }

    public String getName() {
        return name;
    }
}